
function getGameCard(game, changeView) {
  const colDiv = document.createElement('div');
  colDiv.classList = 'col mb-4';

  const cardDiv = document.createElement('div');
  cardDiv.classList = 'card';
  cardDiv.style.width = '18rem';

  const cardImg = document.createElement('img');
  cardImg.classList = 'card-img-top';
  cardImg.alt = game.name;
  cardImg.src = 'images/' + game.type.toLowerCase() + '.png';

  const cardBodyDiv = document.createElement('div');
  cardBodyDiv.classList = 'card-body';

  const cardTitleH5 = document.createElement('h5');
  cardTitleH5.classList = 'card-title';
  cardTitleH5.textContent = game.name;

  const cardTextP = document.createElement('p');
  cardTextP.classList = 'card-text';
  cardTextP.textContent = game.description;

  const enterBtn = document.createElement('button');
  enterBtn.classList = 'btn btn-primary btn-sm';
  enterBtn.innerHTML = '<i class="bi bi-box-arrow-in-right me-2"></i>Entrar';
  enterBtn.addEventListener('click', changeView);

  cardBodyDiv.append(cardTitleH5, cardTextP, enterBtn);
  cardDiv.append(cardImg, cardBodyDiv);
  colDiv.append(cardDiv);

  

  return colDiv;
}

function getGameplayCard(gameResult) {
  const cardDiv = document.createElement('div');
  cardDiv.classList = 'card mb-3';

  const cardBodyDiv = document.createElement('div');
  cardBodyDiv.classList = 'card-body';

  const rowDiv = document.createElement('div');
  rowDiv.classList = 'row text-center';

  const firstColDiv = document.createElement('div');
  firstColDiv.classList = 'col-3';

  gameResult.diceValues.forEach(val => {
    const diceIcon = document.createElement('i');
    diceIcon.classList = 'me-3 bi bi-dice-' + val;
    firstColDiv.append(diceIcon);
  });

  const secondColDiv = document.createElement('div');
  secondColDiv.classList = 'col-4 text-success';
  secondColDiv.textContent = gameResult.winner ? 'Has guanyat!' : 'Has perdut!';

  const thirdColDiv = document.createElement('div');
  thirdColDiv.classList = 'col-5 text-muted fst-italic';
  thirdColDiv.textContent = gameResult.createdAt;

  rowDiv.append(firstColDiv, secondColDiv, thirdColDiv);
  cardBodyDiv.append(rowDiv);
  cardDiv.append(cardBodyDiv);

  return cardDiv;
}