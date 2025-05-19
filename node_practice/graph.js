



class Graph {


    constructor() {
        this.adjList = new Map();
    }

    addVertex(vertex) {
        if(
            !this.adjList.has(vertex)
        ) {
            this.adjList.set(vertex, []);
        }
    }

    addEdge(v1, v2) {
        this.addVertex(v1);
        this.addVertex(v2);
        this.adjList.get(v1).push(v2);
        this.adjList.get(v2).push(v1);  // remove this line for directed graph
    }

    printGraph() {
        for(let [vertex, edges] of this.adjList) {
            console.log(`${vertex} -> ${edges.join(', ')}`);
        }
    }

    dfs() {
        let visited = new Set();
        let res = [];

        for(let [v, e] of this.adjList) {
            if(!visited.has(v)) this._dfs(visited, res, v);
        }
        
        res.forEach((item) => console.log(item));
    }


    _dfs(visited, res, s) {
        visited.add(s);
        res.push(s);

        for(let e of this.adjList.get(s)) {
            if(!visited.has(e)) this._dfs(visited, res, e);
        }
    }


    bfs() {
        let visited = new Set();
        let q = ['A'];
        while(q.length > 0) {
            const vertex = q.shift();
            if(visited.has(vertex)) {
                continue;
            }

            console.log(vertex);
            visited.add(vertex);

            for(let neighbor of this.adjList.get(vertex)) {
                if(!visited.has(neighbor)) {
                    q.push(neighbor);
                }
            }
        }
    }

}








const graph = new Graph();

graph.addEdge('A', 'C');
graph.addEdge('A', 'B');
graph.addEdge('B', 'D');
graph.addEdge('C', 'E');
graph.addEdge('D', 'E');
graph.addEdge('E', 'F');

graph.printGraph();

graph.dfs();



graph.bfs();


