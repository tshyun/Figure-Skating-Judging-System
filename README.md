# My Personal Project

## Judging System for Figure Skating

### 1. What will this application do?

      - suit all four Olympic disciplines (men's single, ladies' singles, pair skating, and ice dance)
      - suit two programs for each disciplines (short program and the free skate)
      - calculate the finally grades for referee
      - present scores for audience

### 2. Who will use it?

      - referee
      - fans of figure skating
      - normal audience
      - coach

### 3. Why is this project interest to me?

       I am a fan of figure skating, so instead of just watching the competition and cheers for the skaters,
       I also want to assume that I am a referee and grades for them, or maybe in the future be a figure
       skating referee. But it is hard and time consuming for me to remeber so many different kinds of
       component and their base values, some deduction and addition points.

       So instead of spend lots of time to do this calculation handly, I would like to design a application,
       which it can help me save lots of time.

## User Stories

- As a user, I want to be able to create a new program
- As a user, I want to be able to add components and elements and scores to my program
- As a user, I want to be able to calculate all the component scores on my program and add it to my list programs
- As a user, I want to be able to output the total score and show it (ui)
- As a user, I want to be able to save my score to file
- As a user, I want to be able to be able to load my last competition score from file

## Important Notes (Updated)

- All these rules are based on 2021-2022 seasons
- All the information can be founded at isu.org, the official website for international skating union
- Try to avoid inputting the base value for each score, since there are lots of executed element and for each element,
under the specific seasons, it has a certain value. If we try to remember each value, it would be a hard and nearly
impossible. So in the future I want to do a match part, and input the library for all executed element. Also for the
base value, besides jump(except in Ice Dance), throw jump(only in pairs), they all have different level, based on the
skater's preform, so the base value also differs, which is not being achieved in my project.

## Phase4: Task2
    Thu Nov 25 12:36:53 PST 2021
    Added skater category: Men, name: CHEN, nationality: CHN
    
    Thu Nov 25 12:37:06 PST 2021
    Added executed element: 4s, with base value: 10.0, half program?(0 for no, 1 for yes): 0
    
    Thu Nov 25 12:37:06 PST 2021
    Added judge score for 4s is [3.0, 4.0, 3.0, 4.0, 3.0, 4.0, 3.0, 4.0, 3.0]
    
    Thu Nov 25 12:37:06 PST 2021
    GOE for 4s is 3.43
    
    Thu Nov 25 12:37:06 PST 2021
    Panel score for 4s is 13.43
    
    Thu Nov 25 12:37:30 PST 2021
    Add program component: Composition, with factor 1.0
    
    Thu Nov 25 12:37:30 PST 2021
    Added judge score for Composition is [9.0, 8.5, 9.0, 8.75, 9.0, 9.25, 9.5, 8.75, 9.0]
    
    Thu Nov 25 12:37:30 PST 2021
    Panel score for Composition is 8.96
Explanations: 
1. If we do not click quit button, instead, we close the desketopFrame, then it will not be any output
in eventLog and consule, since the print EventLog action only be preformed when we call a method in
quitAction.
2. If we do not click buttons like "Add This Skater's Information", "Add Execited Element", "Add Program
Component", we would not get any event logged.
3. If we click quit button but runs an error, we would not get any event logged, since the program has been breaked at
some points before the events has been logged.


## Phase4: Task3

- Since in ExexutedElement, and ProgramComponent class, it all have the same field, like element name, judge scores,
and score of panel. So I can extract those field and the relative methods to a new class. And let ExecutedElement and
Program Component to extend this new class. Also for ListOfExuctedElementcnad ListOfProgramComponent, the roundOff() and
sumScoreOfPanel() method are the same, I can also extract them to a new class and let those two class extends from the
new one.




#### Shaoyun Tong
#### Last updated : 11/25/2021
