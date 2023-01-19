function greeting() {
    alert("Hello Spring Developer!!");
}
document.getElementById("mouseOver").addEventListener("mouseover", mouseOver);
document.getElementById("mouseOver").addEventListener("mouseout", mouseOut);

function mouseOver() {
  document.getElementById("mouseOver").style.color = "red";
}

function mouseOut() {
  document.getElementById("mouseOver").style.color = "black";
}
function currentTime() {
  document.getElementById("displayDate").innerHTML = Date();
}