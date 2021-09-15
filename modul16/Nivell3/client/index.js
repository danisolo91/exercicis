document.addEventListener("DOMContentLoaded", function (event) {

  /* ---- DOM ELEMENTS ------------------------------------------------ */
  // Login view elements
  const loginView = document.getElementById('loginView');
  const loginForm = document.getElementById('loginForm');
  const loginRegisterBtn = document.getElementById('registerBtn');

  // Register view elements
  const registerView = document.getElementById('registerView');
  const registerForm = document.getElementById('registerForm');
  const registerLoginBtn = document.getElementById('loginBtn');

  // Homepage view elements
  const homepageView = document.getElementById('homepageView');
  const logoutBtn = document.getElementById('logoutBtn');
  const usernameSpan = document.getElementById('usernameSpan');
  const availableGamesDiv = document.getElementById('availableGames');

  // Game view elements
  const gameView = document.getElementById('gameView');
  const goBackBtn = document.getElementById('goBackBtn');

  // Toast elements
  const toastElem = document.getElementById('liveToast');
  const toastBody = document.getElementById('toastBody');
  const toast = new bootstrap.Toast(toastElem);


  /* ---- APP VARIABLES ------------------------------------------------ */
  const views = {
    LOGIN: 'login',
    REGISTER: 'register',
    HOMEPAGE: 'homepage',
    GAME: 'game'
  }

  const appState = {
    loggedUser: {},
    games: [],
    selectedGame: ''
  }

  /* ---- EVENT LISTENERS ----------------------------------------------- */
  loginForm.addEventListener('submit', login);
  loginRegisterBtn.addEventListener('click', () => showView(views.REGISTER));
  logoutBtn.addEventListener('click', logout);
  registerLoginBtn.addEventListener('click', () => showView(views.LOGIN));
  registerForm.addEventListener('submit', register);
  goBackBtn.addEventListener('click', () => showView(views.HOMEPAGE));


  /* ---- FUNCTIONS ------------------------------------------------------ */
  function register(e) {
    e.preventDefault();
    saveUser(e.target.username.value, e.target.password.value, e.target.hideUsername.checked)
      .then(res => {
        if (res.message) {
          showToast(res.message);
        }
      })
      .catch(error => showToast(error));
  }

  function login(e) {
    e.preventDefault();
    getUser(e.target.username.value, e.target.password.value)
      .then(res => {
        if (res.message) {
          showToast(res.message);
        } else {
          appState.loggedUser = res;
          showView(views.HOMEPAGE);
        }
      })
      .catch(error => {
        showToast(error)
      });
  }

  function logout(e) {
    appState.loggedUser = {};
    appState.games = [];
    appState.selectedGame = '';
    showView(views.LOGIN);
  }

  // Carrega les dades dinàmiques de la homepage 
  function loadHomepageView() {
    usernameSpan.textContent = appState.loggedUser.username;
    if (appState.games.length === 0) {
      getAvailableGames(appState.loggedUser.token).then(res => {
        res.forEach(game => {
          appState.games.push(game);
          const gameCard = getGameCard(game, () => {
            appState.selectedGame = game.type;
            showView(views.GAME);
          });
          availableGamesDiv.append(gameCard);
        });
      });
    }
  }

  // Carrega les dades dinàmiques de la game view
  function loadGameView() {
    console.log("loadGameView");
  }

  function showView(view) {
    switch (view.toLowerCase()) {
      case views.LOGIN:
        loginView.classList = '';
        registerView.classList = 'd-none';
        homepageView.classList = 'd-none';
        gameView.classList = 'd-none';
        break;
      case views.REGISTER:
        loginView.classList = 'd-none';
        registerView.classList = '';
        homepageView.classList = 'd-none';
        gameView.classList = 'd-none';
        break;
      case views.HOMEPAGE:
        loadHomepageView();
        loginView.classList = 'd-none';
        registerView.classList = 'd-none';
        homepageView.classList = '';
        gameView.classList = 'd-none';
        break;
      case views.GAME:
        loadGameView();
        loginView.classList = 'd-none';
        registerView.classList = 'd-none';
        homepageView.classList = 'd-none';
        gameView.classList = '';
        break;
      default:
        console.log('setView error');
    }
  }

  function showToast(message) {
    toastBody.textContent = message;
    toast.show();
    setTimeout(function () { toast.hide(); }, 3000);
  }
});