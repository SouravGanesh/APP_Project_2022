const countryEle = document.getElementById('searchBtn');

document.getElementById('logo').addEventListener('click', () => {
  document.getElementById('cards').style.display = 'none';
  document.getElementById('home').style.display = 'block';
})

countryEle.addEventListener('click', getGlobal);

function createCards(data) {
  for (let i = 0; i < data.length; i++) {
    const divEle = document.createElement('div');
    divEle.className = 'card ';
    divEle.style = 'width: 18rem; border-style: solid;';

    const imgEle = document.createElement('img');
    imgEle.src = data[i].imageUrl;
    imgEle.className = 'card-img-top';

    const pEle = document.createElement('p');
    pEle.innerHTML = "Character Name :" +  data[i].name;
    pEle.className = 'card-text';
    
    const p1Ele = document.createElement('p');
    p1Ele.innerHTML ="Movie : " +  data[i].finalFilmName;
    p1Ele.className = 'card-text';
    
    
    const p2Ele = document.createElement('p');
    p2Ele.innerHTML = "TV Shows :" + data[i].finalShowName;
    p2Ele.className = 'card-text';
    
    

    const divEle2 = document.createElement('div');
    divEle2.className = 'card-body';
    divEle2.appendChild(pEle);
    divEle2.appendChild(p1Ele);
     divEle2.appendChild(p2Ele);

    divEle.appendChild(imgEle);
    divEle.appendChild(divEle2);

    document.getElementById('cards').appendChild(divEle);
  }
}
function getGlobal() {
  document.getElementById('home').style.display = 'none';
   document.getElementById('cards').style.display = 'block';
  var searchQuery = document.getElementById('input_data').value;
  var filterBy;
  if (document.getElementById('Movie').checked) {
    filterBy = "movie";
  } else if (document.getElementById('Show').checked) {
    filterBy = "show";
  } else {
    filterBy = "all";
  }
  let COUNTRY_BY_CODES = `http://localhost:8081/APP/register?search=${searchQuery}&filterBy=${filterBy}`;
  //let COUNTRY_BY_CODES = `http://localhost:8081/APP/register`;

  fetch(COUNTRY_BY_CODES)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      createCards(data);
    });
}
