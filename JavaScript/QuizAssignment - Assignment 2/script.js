// DOM elements
const startScreen = document.getElementById("startScreen");
const gameScreen = document.getElementById("gameScreen");
const endScreen = document.getElementById("endScreen");
const startButton = document.getElementById("startButton");
const playAgainButton = document.getElementById("playAgain");
const questionNo = document.getElementById("questionIndex");
const questionText = document.getElementById("questionText");
const optionsButtons = document.querySelectorAll(".option");
const timerDisplay = document.getElementById("timer");
const feedbackDisplay = document.getElementById("feedback");
const runningScore = document.getElementById("currentScore");
const finalScoreDisplay = document.getElementById("finalScore");
const difficultySetting = document.getElementsByClassName("difficultySelector");
const categoryValues = document.getElementById("categorySelector");
const levels = ["easy", "medium", "hard"];
var difficulty = "";

// Game states and flags
let category = categoryValues.value;
let questions = [];
let currentQuestionIndex = 0;
let score = 0;
let timer;
let timeLeft = 15;
let answered = false;

// Event Listeners
startButton.addEventListener("click", startGame);

Array.from(difficultySetting).forEach((option, index) => {
  option.addEventListener("click", () => {
    difficulty = levels[index];
  });
});

playAgainButton.addEventListener("click", () => {
  endScreen.classList.add("hidden");
  startScreen.classList.remove("hidden");
  resetGame();
});

/*
onClick for each buttons on the answers.
if any of the button is clicked and the question is not answered then the system checks for the answer.
*/
optionsButtons.forEach((button) => {
  button.addEventListener("click", () => {
    if (!answered) {
      clearInterval(timer);
      checkAnswer(button);
    }
  });
});

function startGame() {
  startScreen.classList.add("hidden");
  gameScreen.classList.remove("hidden");
  if (difficulty === "") {
    difficulty = levels[0];
  }
  category = categoryValues.value;
  fetchQuestions();
}

function arrayRandomizer(arr) {
  return arr.sort(() => Math.random() - 0.5);
}

function fetchQuestions() {
  fetch(
    `https://opentdb.com/api.php?amount=20&category=${category}&difficulty=${difficulty}&type=multiple`
  )
    .then((response) => response.json())
    .then((data) => {
      questions = data["results"].map((q) => {
        const question = q.question;
        const correct = q.correct_answer;
        const incorrect = q.incorrect_answers;
        const options = arrayRandomizer([...incorrect, correct]);
        return {
          question,
          options,
          correctAnswer: correct,
        };
      });
      currentQuestionIndex = 0;
      score = 0;
      showQuestion();
    })
    .catch((err) => {
      feedbackDisplay.textContent =
        "Error loading questions. Please try again.";
      console.error(err);
    });
}

function showQuestion() {
  resetOptionsStyles();
  answered = false;
  feedbackDisplay.textContent = "";
  questionNo.textContent = currentQuestionIndex + 1;
  if (currentQuestionIndex >= questions.length) {
    return endGame();
  }
  const currentQuestion = questions[currentQuestionIndex];
  questionText.textContent = currentQuestion.question;
  optionsButtons.forEach((button, index) => {
    button.textContent = currentQuestion.options[index];
    button.disabled = false;
  });
  timeLeft = 15;
  timerDisplay.textContent = `Time: ${timeLeft}`;
  startTimer();
}

function startTimer() {
  timer = setInterval(() => {
    timeLeft--;
    timerDisplay.textContent = `Time: ${timeLeft}`;
    if (timeLeft <= 0) {
      clearInterval(timer);
      answered = true;
      showCorrectAnswer();
      feedbackDisplay.textContent = `Time's up! The correct answer is highlighted in green.`;
      disableOptions();
      setTimeout(() => {
        currentQuestionIndex++;
        showQuestion();
      }, 3000);
    }
  }, 1000);
}

/**
 * Highlights the correct answer with green
 * using in case timer runs out.
 */
function showCorrectAnswer() {
  const currentQuestion = questions[currentQuestionIndex];
  optionsButtons.forEach((button) => {
    if (button.textContent === currentQuestion.correctAnswer) {
      button.classList.add("correct");
    }
  });
}

/**
 * Checks the Answer.
 * If the answer is correct, highlight in Green else Red
 */
function checkAnswer(selectedButton) {
  answered = true;
  disableOptions();
  const currentQuestion = questions[currentQuestionIndex];
  const selectedAnswer = selectedButton.textContent;
  if (selectedAnswer === currentQuestion.correctAnswer) {
    selectedButton.classList.add("correct");
    feedbackDisplay.textContent = "Correct!";
    score++;
    runningScore.textContent = score;
  } else {
    selectedButton.classList.add("incorrect");
    feedbackDisplay.textContent = "Incorrect!";
    optionsButtons.forEach((button) => {
      if (button.textContent === currentQuestion.correctAnswer) {
        button.classList.add("correct");
      }
    });
  }
  setTimeout(() => {
    currentQuestionIndex++;
    showQuestion();
  }, 3000);
}

/**
 * Disables the ability to click on the buttons.
 */
function disableOptions() {
  optionsButtons.forEach((button) => {
    button.disabled = true;
  });
}

/*
 * Reset the Styles of the options
 * i.e Red and Green Colors for the buttons.
 */
function resetOptionsStyles() {
  optionsButtons.forEach((button) => {
    button.classList.remove("correct", "incorrect");
  });
}

/**
 * Remove the UI and show scores
 */
function endGame() {
  gameScreen.classList.add("hidden");
  endScreen.classList.remove("hidden");
  finalScoreDisplay.textContent = `Your Score: ${score} / ${questions.length}`;
}

function resetGame() {
  questions = [];
  currentQuestionIndex = 0;
  questionNo.textContent = "1";
  difficulty = "";
  optionsButtons.forEach((button) => {
    button.textContent = "";
    button.disabled = false;
  });
  questionText.textContent = "";
  score = 0;
  feedbackDisplay.textContent = "";
}
