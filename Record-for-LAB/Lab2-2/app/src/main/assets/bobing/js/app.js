function init(a, b, c, d, e, f) {
    var container = document.getElementById('dicebox');
    $('.redpacket').remove();
    var arr = this.randomFun();
    container.appendChild(createDice(a, 0));
    container.appendChild(createDice(b, 1));
    container.appendChild(createDice(c, 2));
    container.appendChild(createDice(d, 3));
    container.appendChild(createDice(e, 4));
    container.appendChild(createDice(f, 5));
    // for (var i = 0; i < 6; i++) {
    //     container.appendChild(this.createDice(arr[i] + 1, i));
    // }
}

function randomFun() {
    var arr = [];
    for (var i = 0; i < 6; i++) {
        arr.push(Math.floor(Math.random() * 6));
    }
    return arr;
}

function createDice(num, i) {
    var image = document.createElement('img');
    image.setAttribute("class", "redpacket");
    image.id = "redpacket" + i;
    image.src = 'img/' + num + '.jpg';
    return image;
}