

class Node {
    constructor(key, value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}


class LRUCache {
    

    
    constructor(capacity) {
        this.capacity = capacity;
        this.map = new Map();

        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }



    get(key) {
        if(this.map.has(key)) {
            let node = this.map.get(key);
            this._remove(node);
            this._add(node);
            return node.value; 
        }
        return -1;
    }


    put(key, value) {
        if(this.map.has(key)) {
            this._remove(this.map.get());
        }
        let newNode = new Node(key, value);
        this._add(newNode);
        this.map.set(key, newNode);

        if(this.map.size > this.capacity ) {
            let lru = this.tail.prev;
            this._remove(lru);
            this.map.delete(lru.key);
        }
    }


    _remove(node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    _add(node) {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;
    }


}



let cache = new LRUCache(2);

cache.put(1, 1);
cache.put(2, 2);
console.log(cache.get(1)); // returns 1

cache.put(3, 3);           // evicts key 2
console.log(cache.get(2)); // returns -1

cache.put(4, 4);           // evicts key 1
console.log(cache.get(1)); // returns -1
console.log(cache.get(3)); // returns 3
console.log(cache.get(4)); // returns 4
