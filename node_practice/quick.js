
function partition(arr, l, r) {
    let pi = arr[r];
    let i = l-1;

    for(let j = l; j < r; j++ ) {
        if(arr[j] <= pi) {
            i++;
            let temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        } 
    }
    // swapping
    let temp = arr[i+1];
    arr[i+1] = arr[r];
    arr[r] = temp;

    return i+1;
}




function quick_sort(arr, l, r) {
    if(r-l <= 1) {
        return;
    }
    
    let pi = partition(arr, l, r);
    quick_sort(arr, l , pi - 1);
    quick_sort(arr, pi + 1, r);
}


let arr = [6, 5, 9, 2, 4];
console.log(arr);

quick_sort(arr, 0, arr.length -1);

console.log(arr);