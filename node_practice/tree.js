

class Node {
    constructor(val) {
        this.value = val;
        this.children = [];
    }


    addChild(node) {
        this.children.push(node);
    }
}

const bigJsonTree = {
  value: 'root',
  children: [
    {
      value: 'child1',
      children: [
        {
          value: 'child1.1',
          children: [
            { value: 'child1.1.1', children: [] },
            { value: 'child1.1.2', children: [] }
          ]
        },
        {
          value: 'child1.2',
          children: [
            { value: 'child1.2.1', children: [] }
          ]
        }
      ]
    },
    {
      value: 'child2',
      children: [
        {
          value: 'child2.1',
          children: [
            { value: 'child2.1.1', children: [] },
            { value: 'child2.1.2', children: [] }
          ]
        }
      ]
    },
    {
      value: 'child3',
      children: [
        {
          value: 'child3.1',
          children: []
        },
        {
          value: 'child3.2',
          children: [
            {
              value: 'child3.2.1',
              children: [
                { value: 'child3.2.1.1', children: [] }
              ]
            }
          ]
        }
      ]
    }
  ]
};



function buildTreeFromJson(jsonNode) {
    const node = new Node(jsonNode.value);


    for(let child of jsonNode.children) {
        const childNode = buildTreeFromJson(child);
        node.addChild(childNode);
    }

    return node;
}



function dfsTree(tree) {
    // if(tree == undefined ) {
    //     return;
    // }
    console.log("printing value : ", tree.value);
    for(let child of tree.children) {
        dfsTree(child);
    }
}

const tree = buildTreeFromJson(bigJsonTree);
dfsTree(tree);



