var score1, score2, currentScore, activePlayer, gameCondition;

// Pointers
var dice = document.getElementById("dice");
var p1Name = document.getElementById("p1n");
var p2Name = document.getElementById("p2n");
var p1Class = document.getElementById("p1");
var p2Class = document.getElementById("p2");
var s1Score = document.getElementById("saved-score-1");
var s2Score = document.getElementById("saved-score-2");
var c1Score = document.getElementById("curr-score-1");
var c2Score = document.getElementById("curr-score-2");
var winnerId = document.getElementById("winner");

// Initialize or Reset the Game
function start() {
  score1 = 0;
  score2 = 0;
  currentScore = 0;
  activePlayer = 1;
  gameCondition = true;

  // Reset the UI
  s1Score.textContent = "0";
  s2Score.textContent = "0";
  c1Score.textContent = "0";
  c2Score.textContent = "0";
  p1Name.textContent = "Player 1";
  p2Name.textContent = "Player 2";
  winnerId.textContent = "";
  dice.style.visibility = "hidden";

  // Set the active player
  p1Class.classList.add("active");
  p2Class.classList.remove("active");
}

function playerSwitch() {
  if (gameCondition) {
    currentScore = 0;
    document.getElementById(
      activePlayer == 1 ? "curr-score-1" : "curr-score-2"
    ).textContent = currentScore;
    activePlayer = activePlayer == 1 ? 2 : 1;
    p1Class.classList.toggle("active");
    p2Class.classList.toggle("active");
    dice.style.visibility = "hidden";
  }
}

start();

document.getElementById("new-game-btn").addEventListener("click", start);

document
  .getElementById("input-pname-1")
  .addEventListener("change", function () {
    p1Name.textContent = this.value || "Player 1";
  });

document
  .getElementById("input-pname-2")
  .addEventListener("change", function () {
    p2Name.textContent = this.value || "Player 2";
  });

document.getElementById("roll-btn").addEventListener("click", () => {
  if (gameCondition) {
    var diceVal = Math.floor(Math.random() * 6) + 1;
    if (diceVal != 1) {
      currentScore += diceVal;
      document.getElementById(
        activePlayer == 1 ? "curr-score-1" : "curr-score-2"
      ).textContent = currentScore;
    } else {
      playerSwitch();
    }
    var diceImg = dice;
    diceImg.style.display = "block";
    dice.style.visibility = "visible";
    diceImg.src = "./images/dice-" + diceVal + ".png";
  }
});

document.getElementById("save-btn").addEventListener("click", () => {
  if (gameCondition) {
    if (activePlayer == 1) {
      score1 += currentScore;
      s1Score.textContent = score1;
      c1Score.textContent = 0;
    } else {
      score2 += currentScore;
      s2Score.textContent = score2;
      c2Score.textContent = 0;
    }
    currentScore = 0;

    if (score1 >= 100 || score2 >= 100) {
      var playerName = document.getElementById(
        "p" + activePlayer + "n"
      ).textContent;
      winnerId.textContent = "Player : " + playerName + " has Won!!";
      gameCondition = false;
      dice.style.visibility = "hidden";
    }
    playerSwitch();
  }
});
