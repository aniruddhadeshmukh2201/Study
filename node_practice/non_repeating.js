


let str = "abbdeagggessdqer";

function fn(str) {
    let map = new Map();

    for(let char of str) {
        map.set(char, (map.get(char) || 0) + 1);
    }

    for(let char of str) {
        if(map.get(char) === 1) {
            return char;
        }
    }

    return null;
}

console.log(fn(str));











