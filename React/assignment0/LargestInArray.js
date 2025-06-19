const findLargest = (arr) => {
    if (arr.length === 0) {
        return null;
    }

    if (arr.length === 1) {
        return arr[0];
    }
    let largest = arr[0];
    for (let i = 1; i < arr.length; i++) {
        if (arr[i] > largest) {
            largest = arr[i];
        }
    }
    return largest;
}

console.log(findLargest([3, 7, 2, 9, 5])); // Output: 9