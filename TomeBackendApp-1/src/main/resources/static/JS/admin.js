fetch("http://localhost:8010/headrefs/getmaxXP")
    .then(function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
            }
        response.json().then(function(maxxp) {
            // console.log(maxxp);
            document.getElementById("maxXPdisp").innerHTML+=maxxp
        })                            
    }).catch(function(error) {
            console.log("Request failed", error);
    })

document.querySelector("#setmaxXP").addEventListener("click", (c) => {
    c.preventDefault()
    document.getElementById("setmaxXP").hidden=true
    document.getElementById("setMaxXPSpace").innerHTML=
    `<form id="setMaxXpForm">
        <div class="form-group" style="max-width: 100px">
            <label for="newMaxXP" >New Max XP</label>
            <input type="number" class="form-control" id="newMaxXP">
        </div>
        <input id="submitMaxXP" type="submit" class="btn btn-primary"></input>
    </form><br>
    <button id="cancel" class="btn btn-primary">Cancel</button>`

    document.querySelector("#cancel").addEventListener("click", (c) => {
        c.preventDefault()
        location.reload()
    })

    document.querySelector("#setMaxXpForm").addEventListener("submit", function(e) {
        e.preventDefault();
        let n = document.querySelector("#setMaxXpForm").elements;
        let newXP = n["newMaxXP"].value;
        let csrfToken = $("meta[name='_csrf']").attr("content")
        // console.log(newXP)
        fetch(`http://localhost:8010/headrefs/setmaxXP/${newXP}`, {
            method: "PUT",
            mode: "cors",
            headers: {
                "X-CSRF-TOKEN": csrfToken
            }
        }).then((response) => {
            console.log(response.status)
            location.reload()
        }).catch(function(error) {
            console.log("Request failed", error);
        })
    })
})
