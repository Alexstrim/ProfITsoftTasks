function deleteContract() {
    console.log(123);
    // return confirm("Are you sure that you want to delete contract?");
}
function getList() {
    return fetch("${pageContext.request.contextPath}/loadContracts", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (resp) {
        var jsonObj = resp.json();
        console.log(jsonObj);
        return jsonObj;
    }).then(function (myObjects) {
        console.log(myObjects[0]);
        return myObjects;
    });
}
console.log(getList());