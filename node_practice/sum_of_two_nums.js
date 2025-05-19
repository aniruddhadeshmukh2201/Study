



let arr = [4, 7, 2, 8, 1, 9];

function fn(arr, target) {
    let s = new Set();

    for(let i = 0; i < arr.length; i++ ) {
        s.add(arr[i]);
    }

    for(let i = 0; i < arr.length; i++ ) {
        if(s.has(target - arr[i])) {
            console.log("found it");
            return;
        }
    }

    console.log("not found");
}



fn(arr, 17);