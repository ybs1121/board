INSERT INTO article(id,title,content) values (1,'ga','ga');
INSERT INTO article(id,title,content) values (2,'na','na');
INSERT INTO article(id,title,content) values (3,'da','da');
INSERT INTO article(id,title,content) values (4,'음식','댓글');
INSERT INTO article(id,title,content) values (5,'영화','좋아요');
INSERT INTO article(id,title,content) values (6,'취미','구독');






INSERT INTO comment(id,article_id,nickname,body) values (1,4,'인간1','치킨');
INSERT INTO comment(id,article_id,nickname,body) values (2,4,'인간2','피자');
INSERT INTO comment(id,article_id,nickname,body) values (3,4,'인간3','치킨');


INSERT INTO comment(id,article_id,nickname,body) values (4,5,'인간1', '어바웃타임');
INSERT INTO comment(id,article_id,nickname,body) values (5,5,'인간2', '뷰티인사이드');
INSERT INTO comment(id,article_id,nickname,body) values (6,5,'인간3', '버켓리스트');

INSERT INTO comment(id,article_id,nickname,body) values (7,6,'인간1','코딩');
INSERT INTO comment(id,article_id,nickname,body) values (8,6,'인간2','음악');
INSERT INTO comment(id,article_id,nickname,body) values (9,6,'인간3','영화');