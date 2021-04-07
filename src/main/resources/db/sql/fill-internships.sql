-- liquibase formatted sql

-- changeset 20210406.[BE-41]-fill-internships

UPDATE internship SET description='As an intern, you’ll gain hands-on experience in diagnosing and resolving user problems. 
Depending on your area of interest, you might flex your coding muscles, keep key projects on track, or ensure network security.',
additional_info='With an IT internship, you’ll have the bandwidth for a successful career. This booming field is expected to grow 12% during the next decade, 
according to the Bureau of Labor Statistics. To get an IT internship, you should have a strong foundation.',
image='https://www.umassmed.edu/globalassets/it/internship/home-slider/remote-internship.png?format=jpeg&quality=80'
WHERE id=1@@

UPDATE internship SET name = 'Cyber Security Intern', description='Job Information NORTHWESTERN MUTUAL LIFE INSURANCE Cyber Security Internship in MILWAUKEE, 
Wisconsin JOB REQUIREMENTS: At Northwestern Mutual, we are strong, innovative and growing. We invest in our people. We care and make a positive difference. ',
additional_info='At Northwestern Mutual, we are strong, innovative and growing. We invest in our people. We care and make a positive difference. What''s the role? 
The Cybersecurity internship is designed to give students an opportunity to apply concepts, protocols and tools acquired through coursework in the real world by 
working side-by-side with our cybersecurity professionals. The internship will focus on areas such as identification and analysis of malicious code, forensics analysis, 
incident handling, and intrusion detection and prevention. Interns may be involved in providing day-to-day support as needed including: Conducting risk analysis / assessments 
of applications or vendors and communicating results to drive proactive remediation Supporting efforts to establish and leverage enterprise identity and access management 
processes Identifying, building and delivering effective security awareness and training campaigns and material Gathering, reporting, analyzing, and delivering various 
security metrics. Participate in logging and monitoring processes, incident response, digital forensics, vulnerability management, and application security/penetration testing.',
image='https://d2q79iu7y748jz.cloudfront.net/s/_headerimage/9d6dde50f0a3387413c7d7381a2abda2'
WHERE id=2@@

UPDATE internship SET name = 'Global IT Internship', description='With over 80 years as an industrial technology leader, Kennametal Inc. delivers productivity to customers 
through materials science, tooling and wear-resistant solutions. Customers across aerospace, earthworks, energy, general engineering and transportation turn to Kennametal 
to help them manufacture with precision and efficiency. Every day approximately 9,000 employees are helping customers in more than 60 countries stay competitive. 
Kennametal generated nearly $1.9 billion in revenues in fiscal 2020.',
additional_info='Kennametal, a global leader in the manufacturing of advanced materials and wear solutions is currently looking to identify summer interns to work on business 
critical projects for the Global Information Technology Group.

The individuals selected will be expected to:

Lead projects and associated tasks to completion under the direction of a member of IT Leadership.
Create IT services and solutions, by converting business and technical specifications into solutions
Work on the development of applications utilizing the programming languages ABAP, Java, Visual Basic, .Net or other technologies such as Adobe, Business Objects, and Portal. 
Assists in research to develop or modify systems or applications.
Document developed solutions, conducts unit testing, maintains and supports solutions.
Implement IT solutions including user training and business customer support.

Candidates successfully completing the summer internship program may be considered for Kennametal’s highly competitive Information Technology Leadership Program (ITLP) 
which is an entry-level rotational program for graduating students.

Experience and Skills:

Minimum GPA of 3.2
Pursuit of a Bachelor’s Degree in Information Technology / Computer Sciences or Technical Field of Study
Exceptional communication skills
Previous exposure to leadership related roles in extracurricular activities
High degree of business professionalism with a focus on integrity, maturity and ethics', 
image='https://d2q79iu7y748jz.cloudfront.net/s/_headerimage/455ea9ca46557e472f5e63a2db0505d9'
WHERE id=3@@

INSERT INTO internship (name, start_date, end_date, start_request_date, end_request_date, description, additional_info, image, format, type_id) 
VALUES('IT INTERN, GMP LABORATORY', '2021-05-30', '2021-06-10', '2021-04-20', '2021-04-25', 'PPD is a leading global Contract Research Organization - currently operating in 
nearly 50 countries! At PPD we are passionate, deliberate, and driven by our purpose - to improve health. Our organization works with a diverse group of pharmaceutical 
companies to help bend the cost and time curve of small molecule, biologic, and vaccine product development.', 'Responsibilities

Assisting team members with assigned tasks in specific project areas, as well as completing tasks in "Student''s Measurable Learning Objectives"
Training within department (attend departmental specific training)
Attending meetings and other learning opportunities
Other tasks under the direction of the PPD supervisor
Qualifications

Education and Experience:

Must be enrolled in an Information Technology Undergraduate program as a rising Junior or rising Senior
Knowledge, Skills and Abilities:

Good communication and interpersonal skills
Good organizational skills and strong attention to detail, with proven ability to handle multiple tasks efficiently and effectively
Good English and grammar skills
Good computer skills, proficient in MS Word, Excel, PowerPoint, etc.', 'https://d2q79iu7y748jz.cloudfront.net/s/_headerimage/a17f9bcef6b0c17de1b5f480eaf223fc', 'ONLINE', 3)@@

INSERT INTO country_internship VALUES(1, 4)@@

INSERT INTO internship_subject VALUES(3, 4)@@

INSERT INTO internship_skill VALUES(4, 8,'HIGH'), (4, 5, 'HIGH'), (4, 1, 'HIGH'), (4, 12, 'HIGH')@@

INSERT INTO internship (name, start_date, end_date, description, additional_info, image, format, type_id) 
VALUES('Information Technology Intern', '2021-04-30', '2021-05-10', 'We are an employee-focused organization that promotes from within and are 
looking for enthusiastic, team-oriented employees to join our growing team. Founded in 1938, Agropur is a top 20 global dairy processor. 
In addition to being one of the largest award-winning cheese manufacturers, Agropur is a leader in high quality and diverse nutritional whey proteins.', 
'We are looking for an Information Technology Intern for the summer of 2021. This role could work out of La Crosse, WI, Eden Prairie, MN or Le Sueur, MN.
The Information Technology Intern works closely with Information Technology Leadership and other IT employees on various Information Technology tasks and projects. The responsibilities of the Information Technology Intern vary based on assignments and department needs.

Provide end user support – both face-to-face and remote.
Design, modify, develop, write and implement software programming applications of low to moderate complexity.
Evaluate user requests to design/modify systems to align with existing or new business processes/goals.
Communicate with internal end users and external customers to determine the requirements of requested applications.
Design, perform, review, and certify the testing and validation of software applications.
Assist as needed in the administration and/or troubleshooting of existing software systems, both off-the-shelf and custom written.
Provide user training for the above-mentioned software systems.
Troubleshoot both hardware & software issues.
Take direction on assignments and then work autonomously or with appropriate team(s) to accomplish assignment objectives.
Acquire current knowledge and understanding of regulations, industry trends, current practices, new developments and applicable laws related to Information Technology in a dairy manufacturing environment.
Thoroughly learn about the Agropur business model and how to run an efficient IT department.
Actively meet and speak with Information Technology employees to learn the various aspects and responsibilities of their jobs.
Learn to work safely and efficiently, while maintaining accuracy.
Successfully pass any assessments or tests required to work within the Information Technology department.
Present a summary of projects and lessons learned at end of internship, if applicable.
Research and perform work on special projects as assigned.
Regular contact with Agropur employees to obtain and provide information.
This position works with highly confidential and business information.
Understand and adhere to Good Manufacturing Practices.
Safety Protocol
Stop any observed unsafe acts and obey facility safety rules and procedures.
Correct or report any observed safety hazards.
Support safety policies and programs.',
 'https://d2q79iu7y748jz.cloudfront.net/s/_headerimage/cc8e9983d77ea9cb405b78220d3432b1', 'ONLINE', 5)@@

INSERT INTO country_internship VALUES(1, 5)@@

INSERT INTO internship_subject VALUES(2, 5)@@

INSERT INTO internship_skill VALUES(5, 1,'HIGH'), (5, 5, 'HIGH'), (5, 3, 'HIGH'), (5, 11, 'HIGH'), (5, 10, 'HIGH')@@

INSERT INTO internship (name, start_date, end_date, description, additional_info, image, format, type_id) 
VALUES('IT Intern - Java/Mobile', '2021-06-30', '2021-07-10', 'Lennox International (LII) is a leading global provider of innovative climate control solutions for heating, 
ventilation, air conditioning, and refrigeration (HVACR) markets.', 
'Beginning over a century ago, Lennox International has built a strong heritage of Innovation and Responsibility. Our position as an innovation leader continually inspires us to promote more efficient energy use and a healthier environment through our product operations. Our engaged and diverse workforce is committed to providing climate control solutions that provide the most value and comfort for our customers.

We are proud to have instilled a shared sense of responsibility and commitment among our approximately 10,000 employees located throughout North America, South America, Europe, Asia, and Australia.
Job Description:
This is an outstanding opportunity to develop professional expertise and contribute to a specialized team. As an intern, the person in this position typically works full-time for a defined period during a schooling break. The intern works closely with experts in the area of specialization or on teams to address critical business needs.
It is anticipated that successful execution will lead to significant career opportunities within the Lennox organization.
Qualifications:
Requires a high school diploma or an equivalent combination of education and experience. Entry Level.

Developing professional knowledge in the area of expertise. Solid written and verbal communication skills. Proficient in Microsoft Office.',
 'https://bentacos.com/wp-content/uploads/2019/05/InternshipBlog00.png', 'ONLINE', 2)@@

INSERT INTO country_internship VALUES(1, 6)@@

INSERT INTO internship_subject VALUES(2, 6)@@

INSERT INTO internship_subject VALUES(3, 6)@@

INSERT INTO internship_skill VALUES(6, 1,'HIGH'), (6, 2, 'HIGH'), (6, 3, 'HIGH'), (6, 4, 'HIGH'), (6, 5, 'HIGH'), (6, 6, 'HIGH'), (6, 7, 'HIGH')@@

