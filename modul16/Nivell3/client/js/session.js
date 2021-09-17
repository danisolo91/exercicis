
/** Les cookies només funcionen si l'aplicació s'executa a un servidor web */

// Decodifica el JWT
function parseJwt (token) {
  var base64Url = token.split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  return JSON.parse(jsonPayload);
};

// crea una cookie amb el nom de l'usuari i la data d'expiració del token
function isValidToken(token) {
  const decodedJwt = parseJwt(token);
  return (decodedJwt.exp > Date.now() / 1000);
}

