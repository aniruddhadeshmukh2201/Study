

function merge(arr, l, mid, r) {
    let i = l;
    let j = mid+1;
    while(i <= mid && j <= r) {
        if(arr[i] <= arr[j]) {
            i++;
        } else {
            let temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            let k = j;

            while(k+1 <= r && arr[k] > arr[k+1]) {
                let temp = arr[k];
                arr[k] = arr[k+1];
                arr[k+1] = temp;
                k++;
            }
            i++;
        }
    }
}


function merge_sort(arr, l, r) {
    if((r-l) <= 1) {
        return;
    }
    let mid = l + (r+l)/2;
    merge_sort(arr, l, mid);
    merge_sort(arr, mid+1, r);
    merge(arr, l, mid, r);
}


let arr = [6, 7, 9, 2, 3];
console.log(arr);
merge_sort(arr, 0, arr.length-1);
console.log(arr);