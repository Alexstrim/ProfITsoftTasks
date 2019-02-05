class Client{
    constructor(){
        this.id = 0;
        this.adress = '';
        this.toString = () => "Client {" + this.id + "; " + this.adress + "}";
    }
}
var client = new Client();
function deleteContract() {
    return confirm("Are you sure that you want to delete contract?");
}
function getList() {
    return fetch("${pageContext.request.contextPath}/loadContracts", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (resp) {
        let jsonObj = resp.json();
        console.log(jsonObj);
        return jsonObj;
    }).then(function (myContracts) {
        for(i in myContracts){
            const $tbody = document.getElementById('tableBody__for_add_js');
            let tr = document.createElement('tr');
            let td1 = document.createElement('td');
            let td2 = document.createElement('td');
            let td3 = document.createElement('td');
            let td4 = document.createElement('td');
            let td5 = document.createElement('td');
            let td6 = document.createElement('td');
            let td7 = document.createElement('td');
            client.id = myContracts[i].client.id;
            client.adress = myContracts[i].client.adress;
            td1.innerHTML = myContracts[i].number;
            td2.innerHTML = client;
            td3.innerHTML = new Date(myContracts[i].dateConclusion);
            td4.innerHTML = new Date(myContracts[i].startDate);
            td5.innerHTML = new Date(myContracts[i].endDate);
            var text = '';
            for(j in myContracts[i].persons){
                text += myContracts[i].persons[j].lastName + "<br>";
            }
            td6.innerHTML = text;
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);

            $tbody.appendChild(tr);
        }
        return myContracts;
    });
}
console.log(getList());