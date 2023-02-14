<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="results" class="beans.ResultListBean" scope="session"/>

<html>


<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/core.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Strigalev Nikita Sergeevich P32111 lab №2</title>
</head>

<body>

<div id="main">
    <div id="hat">
                    <div id="my_name" class="left-labeled">Strigalev Nikita Sergeevich - </div>
                    <div id="group" class="left-labeled"> P32111 </div>
                    <div id="variant" class="right-labeled"> Variant №14321 </div>
    </div>

    <div>
        <section class="main-graph-part">
            <h2 class="main-header">
              <span class="main-header-text">
                График
              </span>
            </h2>
            <div class="graph-part">
                <canvas class="canvas" width="352" height="352">Интерактивная область графика</canvas>
                <p class="validate-text" id="paint-status"></p>
            </div>
        </section>

        <section class="main-data-part">
            <h2 class="main-header">
              <span class="main-header-text">
                Данные
              </span>
            </h2>
            <form class="main-form" action="/web-lab2" method="POST">
                <p class="form-text">Введите координаты точки</p>
                <p class="validate-text" id="r-status"></p>
                <p class="validate-text" id="y-status"></p>
                <p class="validate-text" id="x-status"></p>

                <div class="main-form-variable-part" id="x-group">
                    <label class="form-variable-name" for="x-select">X</label>
                    <label class="checkbox-label">
                        <input type="checkbox" id="x-button1" name="x" value="-4">
                        -4
                    </label>
                    <label class="checkbox-label">
                        <input type="checkbox" id="x-button2" name="x" value="-3">
                        -3
                    </label>
                    <label class="checkbox-label">
                        <input type="checkbox" id="x-button3" name="x" value="-2">
                        -2
                    </label>
                    <label class="checkbox-label">
                    <input type="checkbox" id="x-button4" name="x" value="-1">
                    -1
                    </label>
                    <label class="checkbox-label">
                    <input type="checkbox" id="x-button5" name="x" value="0">
                    0
                    </label>
                    <label class="checkbox-label">
                    <input type="checkbox" id="x-button6" name="x" value="1">
                    1
                    </label>
                    <label class="checkbox-label">
                    <input type="checkbox" id="x-button7" name="x" value="2">
                     2
                     </label>
                     <label class="checkbox-label">
                     <input type="checkbox" id="x-button8" name="x" value="3">
                     3
                     </label>
                     <label class="checkbox-label">
                     <input type="checkbox" id="x-button9" name="x" value="4">
                     4
                     </label>

                </div>

                <div class="main-form-variable-part">
                    <label class="form-variable-name" for="text-y">Y</label>
                    <input class="form-variable-text" id="text-y" type="text" name="y" maxlength="10" placeholder="Введите Y от -5 до 3">
                </div>

                <div class="main-form-variable-part">
                    <label class="form-variable-name" for="text-r">R</label>
                    <input class="form-variable-text" id="text-r" type="text" name="r" maxlength="10" placeholder="Введите R от 1 до 4">
                </div>

                <input id="hidden_timezone" type="hidden" name="timezone" value="">
                <input id="hidden_empty" type="hidden" name="empty" value="">
                <input id="hidden_table" type="hidden" name="table" value="">

                <button class="form-submit-button"
                            type="submit">Отправить</button>

            </form>
        </section>
    </div>

    <section class="main-table-part">
        <h2 class="main-header">
            <span class="main-header-text">
              Таблица
            </span>
        </h2>
        <div class="table-container">
            <table class="main-table", id="result-main-table">
                <tr class="main-table-header">
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Время запроса</th>
                    <th>Время выполнения (в микросекундах)</th>
                    <th>Результат работы</th>
                </tr>
                <c:forEach var="result" items="${results.results}">
                                    <tr>
                                        <td class="table-result">${result.x}</td>
                                        <td class="table-result">${result.y}</td>
                                        <td class="table-result">${result.r}</td>
                                        <td class="table-result">${result.nowTime}</td>
                                        <td class="table-result">${result.executeTime}</td>
                                        <td class="table-result">${result.inArea}</td>
                                    </tr>
                </c:forEach>

            </table>
        </div>
    </section>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
