# Lab 6 - Data Processing

>Course: CSCI 2020U: Software Systems Development and Integration

[![git](https://badgen.net/badge/icon/git?icon=git&label)](https://git-scm.com)

## Overview

In this lab, you will get acquainted with sending data back and forth from client to server.

A lot of data processing happens on the backend since there are usually other moving parts at play, usually
a database, authentication, internal routing, etc.

In this lab, you will send the entire `<table id="chart">` element to the server to be processed into other formats.
Namely JSON, CSV and XML, all of which you will implement.

>Databases are out of the scope of this course until `CSCI 3030U - Database Systems & Concepts`
>where you will become familiar with database theory and structure.

## Lab Work

This is an extension from lab 5 meaning that you'll have to copy over the following files:

- `client/`
  - `index.html`
  - `js/main.js`
  - `css/styles.css`
- `api/java/com/example/lab6`
  - `StudentResource.java`

You'll implement the following files:

- `api/java/com/example/lab6`
  - `FileFormatter.java`
  - `Student.java`

See the subdirectories on how to modify your code.
