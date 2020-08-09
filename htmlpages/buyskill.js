document.querySelector("#armourdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Armour Tree"
    document.getElementById("picspace").innerHTML="<img src='armourtree.png' width=100%>"
})
document.querySelector("#bodydrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Body Tree"
    document.getElementById("picspace").innerHTML="<img src='bodytree.png' width=100%>"
})
document.querySelector("#resdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Resistance Tree"
    document.getElementById("picspace").innerHTML="<img src='resistancetree.png' width=100%>"
})
document.querySelector("#magicdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Magic Tree"
    document.getElementById("picspace").innerHTML="<img src='magictree.png' width=100%>"
})
document.querySelector("#weapondrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Weapon Tree"
    document.getElementById("picspace").innerHTML="<img src='weapontree.png' width=100%>"
})