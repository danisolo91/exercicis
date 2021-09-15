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
    },
  }).then(res => res.json());
}