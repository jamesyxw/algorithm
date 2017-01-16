package main

import (
	"fmt"
	"net/http"
)

func main() {
	fmt.Printf("hello, world\n")
	client := &http.Client{}
	resp, err := client.Get("http://gobootcamp.com")
	fmt.Printf(resp)
}
