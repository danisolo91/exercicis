
// Decodifica el JWT
function parseJwt (token) {
  var base64Url = token.split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  return JSON.parse(jsonPayload);
};

// comprova si el token está expirat o no
function isValidToken(token) {
  const decodedJwt = parseJwt(token);
  return (decodedJwt.exp > Date.now() / 1000);
}

