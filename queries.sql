-- Part 1: Test it with SQL
 SELECT * FROM job;
-- yields (int id,  varchar employer, varchar name, varchar skills)

-- Part 2: Test it with SQL
SELECT name FROM employer WHERE location = "St. Louis City";

-- Part 3: Test it with SQL
DROP TABLE job;

-- Part 4: Test it with SQL
-- Line 1: DISTINCT: only return unique values. And only return the columns in skill table
-- Line 3: only return skills that appear in the job_skills table, i.e. skills that are attached to a job
SELECT DISTINCT id, name, description FROM skill
INNER JOIN job_skills
ON skill.id = job_skills.skills_id
ORDER BY name ASC;