const URL = 'http://localhost:8080'

// login
async function getUser(username, password) {
  return fetch(URL + '/login', {
    method: 'POST',
    body: new URLSearchParams({
      'username': username,
      'password': password
    })
  }).then(res => res.json());
}

// register
async function saveUser(username, password, hideUsername) {
  return fetch(URL + '/players', {
    method: 'POST',
    body: new URLSearchParams({
      'username': username,
      'password': password,
      'hideUsername': hideUsername
    })
  }).then(res => res.json());
}

// get available games
async function getAvailableGames(accessToken) {
  return fetch(URL + '/games', {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + accessToken
    }
  }).then(res => res.json());
}

// get user gameplays for a particular game type
async function getUserGameplays(user, type) {
  return fetch(URL + '/players/' + user.id + '/games?gameType=' + type, {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + user.token
    }
  }).then(res => res.json());
}

// play game
async function playGame(user, type) {
  return fetch(URL + '/players/' + user.id + '/games', {
    method: 'POST',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + user.token
    },
    body: new URLSearchParams({
      'gameType': type
    })
  }).then(res => res.json());
}

// delete user gameplays
async function deleteGameplays(user, type) {
  return fetch(URL + '/players/' + user.id + '/games', {
    method: 'DELETE',
    headers: {
      'Authorization': 'Bearer ' + user.token
    },
    body: new URLSearchParams({
      'gameType': type
    })
  }).then(res => res.json());
}

// retorna els rankings de l'usuari
async function getUserRankings(user) {
  return fetch(URL + '/players/ranking/' + user.id, {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + user.token
    }
  }).then(res => res.json());
}

// retorna els rankings de tots els jocs
async function getRankings(user) {
  return fetch(URL + '/players/ranking', {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + user.token
    }
  }).then(res => res.json());
}

// retorna el millor jugador
async function getBestPlayer(user, type) {
  return fetch(URL + '/players/ranking/winner?gameType=' + type, {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + user.token
    }
  }).then(res => res.json());
}

// retorna el pitjor jugador
async function getWorstPlayer(user, type) {
  return fetch(URL + '/players/ranking/loser?gameType=' + type, {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Authorization': 'Bearer ' + user.token
    }
  }).then(res => res.json());
}