 $(function(){
    let paint = $(".canvas");
    let xval;

function checkR(){
    let input = $("#text-r");
    let y = input.val();
    y = y.replace(",", ".");
    if (isNumber(y) && y <= 4 && y >= 1){
            $('#r-status').text("");
            return true;
        }
        else{
            $('#r-status').text("Проверьте правильность переменной R (1 <= R <= 4)");
            return false;
        }
}

function checkX(){
        let values = [document.getElementById("x-button1").checked, document.getElementById("x-button2").checked, document.getElementById("x-button3").checked,
                             document.getElementById("x-button4").checked, document.getElementById("x-button5").checked, document.getElementById("x-button6").checked,
                             document.getElementById("x-button7").checked, document.getElementById("x-button8").checked, document.getElementById("x-button9").checked];
        let count = 0;
            values.forEach(element => element == true ? count = count + 1 : count = count);
            if (count >= 1){
                    $('#x-status').text("");
                    return true;
                }
                else{
                    $('#x-status').text("Должно быть выбрано хотя бы одно значение переменной X");
                    return false;
                }
}

function checkY(){
    let input = $("#text-y");
    let y = input.val();
    y = y.replace(",", ".");
    if (isNumber(y) && y <= 3 && y >= -5){
        $('#y-status').text("");
        return true;
    }
    else{
        $('#y-status').text("Проверьте правильность переменной Y (-5 <= Y <= 3)");
        return false;
    }
}

function validate(){
    x = checkX();
    y = checkY();
    r = checkR();
    return x && y && r;
}

function isNumber(a){
    return isFinite(a) && !isNaN(parseFloat(a));
}

function clearPaint(){
    paint[0].getContext('2d').clearRect(0, 0, paint.width(), paint.height());
}

function draw(x, y){
    //clearPaint();
    if (x > paint.width() || x < -paint.width() || y > paint.height() || y < -paint.height()) {
    }
    else{
        let l = paint[0].getContext('2d');
                l.setLineDash([1.5, 1.5]);
                l.beginPath();
                l.moveTo(x, 175);
                l.lineTo(x, y);
                l.moveTo(176, y);
                l.lineTo(x, y);
                l.stroke();
                l.fillStyle = "#ff0000";
                l.arc(x, y, 2, 0, 2 * Math.PI);
                l.fill();
    }
   }


$('.form-text').on('click', function(event) {
    if (!validate()) {
      event.preventDefault();
    } else {
      $('#hidden_empty').val("false");
      $('#hidden_timezone').val(new Date().getTimezoneOffset());
    }
  });

paint.on('click', function(event){
    if (!checkR()){
        $('#r-status').text("Проверьте правильность переменной R (1 <= R <= 4)");
        $('#paint-status').text("Невозможно определить координаты точки, проверьте правильность переменной R");
        return;
    }
    else{
        $('#r-status').text("");
        $('#paint-status').text("");
        let radius = $("#text-r").val().replace(",", ".");
        let yValue = (-event.offsetY + 176) / 70 / 2;
        let xValue = (event.offsetX - 175) / 70 / 2;
        const a = [-4.0, -3.0, -2.0, -1,0, 0.0, 1.0, 2.0, 3.0, 4.0];
        let answer;
        let difference = Infinity;
        for (let i = 0; i < a.length; i++) {
              if (Math.abs(xValue * radius - a[i]) < difference) {
                difference = Math.abs(xValue * radius - a[i]);
                answer = a[i];
              }
            }
        $('#paint-status').text(" x = " + xValue + " R; y = " + yValue + " R" + " (x; nearest_enum_x; y;) = (" + xValue * radius + "; " + answer + "; " + yValue * radius + ")");
        draw(xValue * 70 * 2 + 175, -(yValue * 70 * 2 - 176) );
        $.post('/web-lab2/', {x: xValue * radius, y: yValue * radius, r: radius, empty: false, table: true}, function(data) {
            const result = data.split("\n");
            let currentCase = '<tr>';
            const table = document.getElementById("result-main-table");
            const newRow = document.createElement("tr");
            const x1 = document.createElement("td");
            const y1 = document.createElement("td");
            const r1 = document.createElement("td");
            const executeTime = document.createElement("td");
            const nowTime = document.createElement("td");
            const inArea = document.createElement("td");
            x1.innerText = result[0];
            y1.innerText = result[1];
            r1.innerText = result[2];
            executeTime.innerText = result[3];
            nowTime.innerText = result[4];
            inArea.innerText = result[5];
            newRow.appendChild(x1);
            newRow.appendChild(y1);
            newRow.appendChild(r1);
            newRow.appendChild(executeTime);
            newRow.appendChild(nowTime);
            newRow.appendChild(inArea);
            table.appendChild(newRow);
        })


    }
});

$('.form-submit-button').on('click', function(event) {
    if (!validate()) {
      event.preventDefault();
    } else {
      $('#hidden_empty').val("false");
      $('#hidden_timezone').val(new Date().getTimezoneOffset());
    }
  });

});
