DELETE FROM Tags_Tasks;
DELETE FROM Projects_Tasks;
DELETE FROM Tags;
DELETE FROM Projects;
DELETE FROM Tasks;

INSERT INTO Tasks (id, title, description, state) VALUES ('00000000-0000-0000-0000-000000000001', 'Water plants', 'Only plants on balcony', 'OPEN');
INSERT INTO Tasks (id, title, description, state) VALUES ('00000000-0000-0000-0000-000000000002', 'Buy groceries', 'For the weekend', 'OPEN');
INSERT INTO Tasks (id, title, description, state) VALUES ('00000000-0000-0000-0000-000000000003', 'Clean the house', '', 'OPEN');
INSERT INTO Tasks (id, title, description, state) VALUES ('00000000-0000-0000-0000-000000000004', 'Sort old clothes', 'Sort old clothes and donate them', 'OPEN');
INSERT INTO Tags (id, name, description) VALUES ('00000000-0000-0000-0000-000000000001', 'At home', 'Tasks to do at home');
INSERT INTO Projects (id, name) VALUES ('00000000-0000-0000-0000-000000000001', 'Become more organized');
INSERT INTO Projects_Tasks (task_id, project_id) VALUES ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001');
INSERT INTO Projects_Tasks (task_id, project_id) VALUES ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000001');
INSERT INTO Tags_Tasks (task_id, tag_id) VALUES ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001');
INSERT INTO Tags_Tasks (task_id, tag_id) VALUES ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001');
INSERT INTO Tags_Tasks (task_id, tag_id) VALUES ('00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000001');