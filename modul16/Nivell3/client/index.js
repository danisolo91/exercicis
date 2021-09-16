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
  const gameTitleH5 = document.getElementById('gameTitle');
  const gameDescriptionP = document.getElementById('gameDescription');
  const gameplaysDiv = document.getElementById('gameplays');
  const throwDicesBtn = document.getElementById('throwDicesBtn');
  const deleteGamesBtn = document.getElementById('deleteGames');
  const statsTabBtn = document.getElementById('stats-tab');
  const userWinningRateSpan = document.getElementById('userWinningRate');
  const generalWinningRateSpan = document.getElementById('generalWinningRate');
  const bestPlayerSpan = document.getElementById('bestPlayer');
  const worstPlayerSpan = document.getElementById('worstPlayer');

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
    selectedGame: {}
  }

  /* ---- EVENT LISTENERS ----------------------------------------------- */
  loginForm.addEventListener('submit', login);
  loginRegisterBtn.addEventListener('click', () => showView(views.REGISTER));
  logoutBtn.addEventListener('click', logout);
  registerLoginBtn.addEventListener('click', () => showView(views.LOGIN));
  registerForm.addEventListener('submit', register);
  goBackBtn.addEventListener('click', () => showView(views.HOMEPAGE));
  throwDicesBtn.addEventListener('click', throwDices);
  deleteGamesBtn.addEventListener('click', deleteUserGameplays);
  statsTabBtn.addEventListener('click', loadStatistics);



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
    appState.selectedGame = {};
    while (availableGamesDiv.lastChild) availableGamesDiv.lastChild.remove();
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
            appState.selectedGame = game;
            showView(views.GAME);
          });
          availableGamesDiv.append(gameCard);
        });
      });
    }
  }

  // Carrega les dades dinàmiques de la game view
  function loadGameView() {
    gameTitleH5.textContent = appState.selectedGame.name;
    gameDescriptionP.textContent = appState.selectedGame.description;
    loadGameplays();
    loadStatistics();
  }

  // Carrega els gameplays del usuari logat
  function loadGameplays() {
    while (gameplaysDiv.lastChild) gameplaysDiv.lastChild.remove();
    getUserGameplays(appState.loggedUser, appState.selectedGame.type).then(res => {
      if (res.length > 0) {
        res.forEach(gameplay => {
          const gameplayCard = getGameplayCard(gameplay);
          gameplaysDiv.append(gameplayCard);
        });
      } else {
        gameplaysDiv.append('No tens cap jugada!');
      }
    });
  }

  // Executa una jugada a un joc determinat
  function throwDices() {
    playGame(appState.loggedUser, appState.selectedGame.type).then(res => {
      const gameplayCard = getGameplayCard(res);
      gameplaysDiv.prepend(gameplayCard);
    });
  }

  // Elimina les jugades d'un usuari a un joc determinat
  function deleteUserGameplays() {
    deleteGameplays(appState.loggedUser, appState.selectedGame.type).then(res => {
      showToast(res.message);
      loadGameplays();
    });
  }

  // carrega les estadístiques d'un joc
  function loadStatistics() {
    loadUserRanking();
    loadGeneralStatistics();
  }

  // carrega el percentatge d'exit de l'usuari a un joc
  function loadUserRanking() {
    getUserRankings(appState.loggedUser).then(res => {
      if (res.message) {
        showToast(res.message);
      } else {
        appState.loggedUser.rankings = res;
      }
    }).then(res => {
      let userRanking = appState.loggedUser.rankings
        .filter(r => r.gameType === appState.selectedGame.type)
        .map(r => r.successRate)[0];
      if (userRanking != undefined) {
        userWinningRateSpan.textContent = userRanking + ' %';
      } else {
        userWinningRateSpan.textContent = 'Sense dades'
      }
    });
  }

  // carrega les estadístiques generals
  function loadGeneralStatistics() {
    loadGameRanking();
    loadBestPlayer();
    loadWorstPlayer();
  }

  // carrega el percentatge d'exit general d'un joc
  function loadGameRanking() {
    getRankings(appState.loggedUser).then(res => {
      let ranking = res.filter(r => r.gameType === appState.selectedGame.type)
        .map(r => r.successRate)[0];
      if (ranking != undefined) {
        generalWinningRateSpan.textContent = (Math.round(ranking * 100) / 100) + ' %';
      } else {
        generalWinningRateSpan.textContent = 'N/A';
      }
    });
  }

  // carrega el millor jugador
  function loadBestPlayer() {
    getBestPlayer(appState.loggedUser, appState.selectedGame.type).then(res => {
      if (res.message) {
        showToast(res.message);
      } else {
        if (res != undefined) {
          let ranking = res.rankings.filter(r => r.gameType === appState.selectedGame.type)
            .map(r => r.successRate)[0];
          bestPlayerSpan.textContent = res.username + ' (' + ranking + ' %)';
        } else {
          bestPlayerSpan.textContent = 'N/A';
        }
      }
    });
  }

  // carrega el pitjor jugador
  function loadWorstPlayer() {
    getWorstPlayer(appState.loggedUser, appState.selectedGame.type).then(res => {
      if (res.message) {
        showToast(res.message);
      } else {
        if (res != undefined) {
          let ranking = res.rankings.filter(r => r.gameType === appState.selectedGame.type)
            .map(r => r.successRate)[0];
          worstPlayerSpan.textContent = res.username + ' (' + ranking + ' %)';
        } else {
          worstPlayerSpan.textContent = 'N/A';
        }
      }
    });
  }

  // mostra la vista rebuda per pantalla
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

  // mostra un toas amb un missatge
  function showToast(message) {
    toastBody.textContent = message;
    toast.show();
    setTimeout(function () { toast.hide(); }, 3000);
  }
});