document.querySelector("#charcreate").addEventListener("submit", function(a) {
    a.preventDefault()

    let v = document.querySelector("#charcreate").elements

    let name = v["charnameinput"].value
    let race = v["chooserace"].value
    let origin = v["regionselect"].value
    let bg = v["backgroundentry"].value

    const newChar = {
        "char_name": name,
        "race": race,
        "origin": origin,
        "char_background": bg,
        "fk_player_id": sessionStorage.getItem("Pid")        
    }
    if (name==="") {
        window.alert("Character name cannot be blank")
    } else {
        saveChar(newChar)
        window.location.href="Account.html"
    }
    
})

function saveChar(data) {
    fetch("http://localhost:8010/character/create", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => response)
    .then(function(data) {
        console.log("Request succeeded with JSON response",data);
    }).then(function(error) {
        console.log("Request failed", error);
    })
}