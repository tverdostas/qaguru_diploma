# Дипломная работа по автоматизации тестирования портала Битрикс.24
<div align="center"> <img src="https://raw.githubusercontent.com/tverdostas/qaguru_diploma/main/images/images.png" alt="Битрикс лого" align="center"></div>
<br>
<div style="border-left: 4px solid #ccc; padding-left: 16px; margin: 20px 0; color: #333; font-size: 16px;">
  <strong>💡 Что такое Битрикс.24?</strong><br>
  Битрикс24 — это комплексная облачная платформа, объединяющая в одном рабочем пространстве CRM, задачи, документы, чаты, звонки, видеоконференции и автоматизацию бизнес-процессов.
</div>
<br>
<div style="border-left: 4px solid #ccc; padding-left: 16px; margin: 20px 0; color: #333; font-size: 16px;">
<strong> 🌿 Почему Битрикс.24 был выбран для дипломного проекта? </strong><br>
На своей практике я видела, что не все компании используют CRM в таком виде, в котором его продает вендор. И конечно, любая доработка может сломать что-либо, снижается стабильность сайта. Автотесты помогут раньше обнаруживать ошибки и снизить нагрузку на ручных тестировщиков.
  
🌿 В дипломной работе был использован пустой и бесплатный Битрикс, но проект вполне может использоваться как шаблон - часть взаимодействий проходит через API, чтобы сделать прогоны быстрее и стабильнее. Для написания таких тестов была использована официальная документация, ознакомиться на сайте вендора: https://helpdesk.bitrix24.ru/open/9721839/.
</div>
Использованы лого с сайта <a target="_blank" href="https://icons8.com/icon/13679/java">Логотип Java Coffee Cup</a> иконка от <a target="_blank" href="https://icons8.com">Icons8</a>

## Технологии и инструменты:
<div align="center">
<a href="https://www.java.com/"><img alt="Java" height="65" src="images/icons/java_logo.png" width="65"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/icons/intellij-idea.png" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/icons/gradle_logo_icon.png" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="45" src="images/icons/junit_5.png" width="45"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/icons/selenide.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/icons/Selenoid.png" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="RestAssured" height="45" src="images/icons/RestAssured.png" width="45"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/icons/jenkins_2.png" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="45" src="images/icons/allure_report.png" width="45"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="45" src="images/icons/allure_testops.png" width="45"/></a> 
<a href="https://telegram.org/"><img alt="Telegram" height="45" src="images/icons/Telegram.png" width="45"/></a>
</div>

<table style="border-collapse: collapse; border: none; width: 100%; margin: 0; background: transparent;">
  <tr>
    <td style="border: none; padding: 8px; vertical-align: top; width: 50%; background: transparent;">
      • Автотесты написаны на языке Java<br>
      • Инструмент сборки Gradle<br>
      • Тестовые фреймворки JUnit 5 и REST-assured<br>
      • Удаленный запуск реализован на Jenkins<br>
      • Отчеты генерируются с использованием Allure report<br>
      • Добавлена интеграция с Allure TestOps
    </td>
    <td style="border: none; padding: 8px; vertical-align: top; width: 50%; text-align: center; background: transparent;">
      <a href="https://rest-assured.io/">
        <a href="images/screenshots/report_in_telegram.png">
      </a>
    </td>
  </tr>
</table>

## Примеры тест-кейсов / проверок:
✅ Проверки меню: все пункты меню отображаются<br>
✅ Создание элементов: сделка создается корректно через UI и API<br>
✅ Проверки авторизации и входа на портал<br>

## Сборка в Jenkins ([ссылка](https://jenkins.autotests.cloud/job/qa_guru_diploma_anastasnanas_2/))
![Сборка в Jenkins](images/screenshots/jenkins_screen.png)

## Allure отчет ([ссылка](https://jenkins.autotests.cloud/job/qa_guru_diploma_anastasnanas_2/7/allure/#))

🌿 При переходе по ссылке будет открыта вкладка Overview с общей статистикой по прогону. При клике на любой сьют (Suites) будут отображены кейсы с понятными названиями + справа шаги, которые были пройдены автотестом (скрин 2). </div>

![Allure Report Screenshot](images/screenshots/allure_отчет.png)<br>
![Allure Report Screenshot](images/screenshots/allure_отчет_2.png)<br>

## Allure TestOps ([ссылка](https://allure.autotests.cloud/launch/49793/?treeId=0))
![Allure Report Screenshot](images/screenshots/test_ops.png)<br>
![Allure Report Screenshot](images/screenshots/allure_test_ops_case.png)<br>
