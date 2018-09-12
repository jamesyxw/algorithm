package datastructure.graph;

import java.util.*;

public class TopologicalSort {

    private static Map<String, Set<String>> buildDependencyLookup (Map<String, List<String>> dependencyGraph) {
        if (dependencyGraph == null || dependencyGraph.isEmpty()) {
            return new HashMap<String, Set<String>>();
        }

        //key is a task, value is set of task that depends on the key task
        Map<String, Set<String>> dependencies = new HashMap<String, Set<String>>();

        //Build a lookup hashmap where we can check what tasks depend on the key task
        for (Map.Entry<String, List<String>> entry : dependencyGraph.entrySet()) {
            //for each task the current task depends on,
            //we create a entry for it if not already exists in the map
            //and put the current task into the set of tasks that depends on it
            for (String taskDepend : entry.getValue()) {
                if (!dependencies.containsKey(taskDepend)) {
                    Set<String> tasksDepend = new HashSet<String>();
                    tasksDepend.add(entry.getKey());
                    dependencies.put(taskDepend, tasksDepend);
                } else {
                    dependencies.get(taskDepend).add(entry.getKey());
                }
            }
        }

        return dependencies;
    }

    /**
     *
     * @param dependencyGraph: key is the task, value is a list of all the task that it depends on
     * @return
     */
    public static List<String> topologicalSort(Map<String, List<String>> dependencyGraph) {
        if (dependencyGraph == null || dependencyGraph.isEmpty()) {
            return new LinkedList<String>();
        }

        Map<String, Set<String>> dependencyLookup = buildDependencyLookup(dependencyGraph);

        List<String> sortedList = new LinkedList<String>();
        Queue<String> todo = new LinkedList<String>(); //the queue of task with no dependency

        for (String task : dependencyGraph.keySet()) {
            //if there are no tasks depending on the current task (no incoming edges)
            if (dependencyLookup.get(task).isEmpty()) {
                todo.add(task);
            }
        }

        while (!todo.isEmpty()) {
            String currentTask = todo.poll();
            sortedList.add(currentTask);

            //Iterate through all the task that current task depends on
            for (String dependency : dependencyGraph.get(currentTask)) {
                dependencyLookup.get(dependency).remove(currentTask);
                //if there is no more tasks depending on the dependency
                if (dependencyLookup.get(dependency).isEmpty()) {
                    todo.add(dependency);
                }
            }
        }

        return sortedList;
    }

}
