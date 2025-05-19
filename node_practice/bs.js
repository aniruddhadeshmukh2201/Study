function bsearch(arr, l, r, t) {
  if (l <= r) {
    let mid = Math.ceil(Math.abs(l + (r-l) / 2));
    if (arr[mid] < t) {
      return bsearch(arr, mid + 1, r, t);
    } else if (arr[mid] > t) {
      return bsearch(arr, l, mid - 1, t);
    } else {
        console.log(" what is goin on : " , l, r, mid, t);
      return mid;
    }
  }
  return -1;
}

let arr = [4, 6, 23, 57, 99];
let target = 99;

console.log(bsearch(arr, 0, arr.length - 1, target));
