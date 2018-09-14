package security;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

public class CryptoUtils {
    private static String keyAlgName = "DSA";
    private static String sigAlgName = "SHA1WithDSA";
    private static String message = "this is a test message";
    private static char[] password = "abc".toCharArray();
    private static int days = 365;
    
    public static KeyStore getKeyStore(String keyStorePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] password = "abc".toCharArray();
        FileInputStream fs = null;
        try {
//            fs = new FileInputStream(keyStorePath);
            ks.load(fs, password);
        } finally {
            if (fs != null) {
                fs.close();
            }
        }
        return ks;
    }
    
    public static CertAndKeyGen generateKeyPair () throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        CertAndKeyGen keyPair = new CertAndKeyGen(keyAlgName, sigAlgName, null);
        keyPair.generate(1024);
        return keyPair;
    }
    
    public static void main(String[] args) {
        try {
            KeyStore ks = getKeyStore("keystore.jks");
            CertAndKeyGen kp = generateKeyPair();
            PrivateKey privKey = kp.getPrivateKey();
            X500Name x500Name = new X500Name("CN=" + "test");
            
            KeyStore.ProtectionParameter protParam =
                    new KeyStore.PasswordProtection(password);
            
            X509Certificate cert = kp.getSelfCertificate(x500Name, days * 24 * 3600);
            Certificate[] certChain = {cert};
            PrivateKeyEntry privKeyEntry = new PrivateKeyEntry(privKey, certChain);
            ks.setEntry("privateKeyAlias", privKeyEntry, protParam);
            
            Signature sig = Signature.getInstance(sigAlgName, "SUN");
            sig.initSign(privKey);
            sig.update(message.getBytes());
            byte[] signature = sig.sign();
            
            
            //======================= Server side =============================
            String signatureStr = new String(signature, "UTF-8");
            System.out.println("Signature: " + signatureStr);
            byte[] signatureByteArray = signatureStr.getBytes("UTF-8");
            Signature serverSig = Signature.getInstance(sigAlgName, "SUN");
            serverSig.initVerify(cert);
            serverSig.update(message.getBytes());
            boolean verified = serverSig.verify(signature);
            System.out.println(verified);
            
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CertificateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
