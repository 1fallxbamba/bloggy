# Bloggy J.A.B.B (Just A Boring Blog)

Bloggy is a basic, free, open-minded blog application where users can register, post and view articles.

## Prerequisites

- First of all, you need to have [Java 11](https://www.java.com/en/download/help/download_options.html) installed. The GUI is made with [JavaFx](https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm), so you'll need to install that too using maven or any other dependency manager.

- bloggy is backed by a [PostgreSQL](https://www.postgresql.org/download/) database so you'll need that installed too. The installation comes with **PgAdmin**, a tool *like PHPMyAdmin* that helps you manage PostgreSQL databases.

- In order to interact with the database [JDBC 4.2 PostgreSQL Driver 42.0](https://jdbc.postgresql.org/download.html) is required.

- After having PostgreSQL installed, you can **restore** (which just means import in PostgreSQL) the *public/bloggy_db* file in the public directory to have the same database as me. [Need help restoring ?](https://www.youtube.com/watch?v=GpZlVEONKpo)
> Note : in PgAdmin, you should be logged in as the default ***postgres*** user in order to successfully restore the database !

Last but not least, you'll need an IDE (I suggest Jetbrain's [IntelliJ IDEA](https://www.jetbrains.com/idea-edu/) which I used to make bloggy).

Got everything ? Now you're all set !

## Running

Having met all the prerequisites, just click the *RUN* button on your IDE et voilà ! bloggy should be up and running now.

>#### Academic purposes *(school stuff huh...)*
> You can find a report describing this project in the *public/bloggy_report.pdf* file.