<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<% LocalDate date=LocalDate.now();
   LocalTime time=LocalTime.now(); %>
   <h1>用户在<%= date.getYear() %>/<%= date.getMonthValue() %>/
    <%= date.getDayOfMonth() %> <br>
    <%= time.getHour() %>:<%= time.getMinute() %>:<%= time.getSecond() %>访问了网页.
   </h1>