package com.dreamteam.TestingSystemNew.service;

import com.dreamteam.TestingSystemNew.model.*;
import com.dreamteam.TestingSystemNew.payload.*;
import com.dreamteam.TestingSystemNew.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConverterJSON converterJSON;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ComplexityRepository complexityRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    GroupsRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    DidacticUnitRepository didacticUnitRepository;

    @Autowired
    HistoryRepository historyRepository;

    Logger log = LoggerFactory.getLogger(UserService.class);

    /*Получение списка тем по дисциплинам*/

     public List<SubjectResponse> getSubjectsAndTopics(Long id) {
         User user = userRepository.findById(id)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with id: " + id)
                 );
         List<TopicResponse> topics = new ArrayList<>();
         List<SubjectResponse> subjects = new ArrayList<>();
         List<DidacticUnitResponse> didacticUnit = new ArrayList<>();
         List<Test> test = testRepository.findAll();
         for (Subject e : user.getSubjects()) {
             for (DidacticUnit d : e.getDidacticUnit()) {
                 for (Topic t : d.getTopics()) {
                     topics.add(new TopicResponse(t.getId_topic(), t.getName(), getDateTimeOfTemplate(test, t)));
                 }
                 didacticUnit.add(new DidacticUnitResponse(d.getId(), d.getName(),topics));
             }

             subjects.add(new SubjectResponse(e.getSubjectId(), e.getName(), didacticUnit));
             didacticUnit = new ArrayList<>();
             topics = new ArrayList<>();
         }
             return subjects;

         }

    /*Сохранение дисциплины*/
    public void saveSubject(String subjectResponse){
        Subject subject = new Subject();
        subject.setName(subjectResponse);
        subjectRepository.save(subject);
    }
    /*сохранение дидактической еденицы*/

    public void saveDidacticUnit(DidacticUnitRequest didacticUnitRequest)
    {
        DidacticUnit didacticUnit = new DidacticUnit();
        didacticUnit.setName(didacticUnitRequest.getTitleDidacticUnit());
        DidacticUnit didacticUnit1 = didacticUnitRepository.save(didacticUnit);

        Subject subject = subjectRepository.getByName(didacticUnitRequest.getTitleSubject());
        subject.getDidacticUnit().add(didacticUnit);
        subjectRepository.save(subject);
    }



    /*Сохранение темы*/
    public void saveTopic(TopicRequest topicRequest){
        Topic topic = new Topic();
        topic.setName(topicRequest.getTitleTopic());
        topic.setCount(topicRequest.getCountQuestion());
        Topic newTopic =  topicRepository.save(topic);
        DidacticUnit didacticUnit = didacticUnitRepository.getByName(topicRequest.getTitleDidactic());
        didacticUnit.getTopics().add(topic);
        didacticUnitRepository.save(didacticUnit);

    }


    /*Список групп для администратора*/

    public List<Groups> getGroupForAdmin(){
        return groupRepository.findAll();
    }

    /*Список групп по дисциплинам для преподователя*/

    public List<GroupResponse> getGroupForTeacher(Long userId){
        List<GroupResponse> groupResponseList = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow();
        for (Teacher t : user.getTeachers()){
            groupResponseList.add(new GroupResponse(t.getSubject().getName(), t.getGroupsList()));
        }
        return groupResponseList;
    }

    /*Добавление нагрузки для преподователя*/

    public void setUserTeacher(TeacherRequest teacherRequest){
        Teacher teacher = new Teacher();
        List<Groups> groupList = new ArrayList<>();
        teacher.setSubject(subjectRepository.getBySubjectId(teacherRequest.getSubjectId()));
        for (Long i : teacherRequest.getGroupsId()){
            groupList.add(groupRepository.getById(i));
        }
        teacher.setGroupsList(groupList);
        Teacher teacher1 = teacherRepository.save(teacher);
        User user = userRepository.findById(teacherRequest.getUserId()).orElseThrow();
        user.getTeachers().add(teacher1);
        userRepository.save(user);
    }


    /*Установка сложности по определенной теме для пользователя*/
    /*
    public void setComplexityForUser(ComplexityRequest complexityRequest){
        Complexity complexity = new Complexity();
        complexity.setName(ComplexityName.values()[complexityRequest.getIndexComplexity()].toString());
        complexity.getTopics().add(topicRepository.findById(complexityRequest.getTopicId()).orElseThrow());
        User user = userRepository.findById(complexityRequest.getUserId()).orElseThrow();
        user.getComplexity().add(complexity);
        complexityRepository.save(complexity);
        userRepository.save(user);
    }

    /*Добавление группы*/
    public void saveGroup(String number){
        Groups group = new Groups();
        group.setName(number);
        groupRepository.save(group);
    }

  /*  Получениеи даты и время из шаблона*/

    public DateTime getDateTimeOfTemplate(List<Test> templates, Topic topic){
        DateTime dateTime = new DateTime();
        for (Test template : templates){
            if (template.getTopic().equals(topic)){
                dateTime.setYear(template.getOpeningDate().getYear());
                dateTime.setMonth(template.getOpeningDate().getMonth());
                dateTime.setDay(template.getOpeningDate().getDay());
                dateTime.setHours(template.getOpeningDate().getHours());
                dateTime.setMin(template.getOpeningDate().getMinutes());
            }
        }
        return dateTime;
    }

    /*Сохранение вопроса*/

    public void saveQuestion(QuestionRequest questionRequest){
        Topic topic = topicRepository.findById(questionRequest.getTopic_id()).orElseThrow();
        Complexity complexity = complexityRepository.findById(questionRequest.getComplexity_id()).orElseThrow();
        Question question = new Question(converterJSON.converterToJSON(questionRequest.getQuestionDTO()), complexity, topic);

        questionRepository.save(question);
    }

    public UserProfile getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return new UserProfile(userId, user.getUserName(), user.getName(), user.getLastName(), user.getMiddleName());
    }


    /*Сохранение шаблона*/

    public void saveTest(TemplateRequest templateRequest){
        Test template = new Test();
        template.setTopic(topicRepository.findById(templateRequest.getTopicId()).orElseThrow());

        template.setOpeningDate(Timestamp.valueOf(
                templateRequest.getOpeningTime().getYear() + "-" +
                        templateRequest.getOpeningTime().getMonth() + "-" +
                        templateRequest.getOpeningTime().getDay() + " " +
                        templateRequest.getOpeningTime().getHours() + ":" +
                        templateRequest.getOpeningTime().getMin() + ":00.0"
        ));

        template.setClosingDate(Timestamp.valueOf(
                templateRequest.getClosingTime().getYear() + "-" +
                        templateRequest.getClosingTime().getMonth() + "-" +
                        templateRequest.getClosingTime().getDay() + " " +
                        templateRequest.getClosingTime().getHours() + ":" +
                        templateRequest.getClosingTime().getMin() + ":00.0"
        ));

        template.setSettings(converterJSON.mapConverterToJSON(templateRequest.getStringMap()));
        template.setUser((List<User>) userRepository.findById(templateRequest.getUserId()).orElseThrow());
        testRepository.save(template);
    }

 //   Получение варианта для пользователя в зависимости от сложности и темы*/

   /* public ArrayList<QuestionDTO> getOption(Long userId, Long topicId){
        Random rand = new Random();
        Complexity complexity = new Complexity();
        int randomIndex;
        int countEasy;
        int countMiddle;
        int countHard;

        ArrayList<QuestionDTO> questionDTOArrayList = new ArrayList<>();
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        Test test = testRepository.getByTopic(topic);
        User user = userRepository.findById(userId).orElseThrow();
        List<Complexity> complexityList = user.getComplexity();
        TemplateJSON templateJSON = new TemplateJSON(converterJSON.parseMapFromJSON(test.getSettings()));

        for (Complexity c : complexityList){
            if (c.getTopics().contains(topic)) complexity = c;
        }

        countEasy = Integer.parseInt(templateJSON.getTest().get(complexity.getName()).split(" ")[0]);
        countMiddle = Integer.parseInt(templateJSON.getTest().get(complexity.getName()).split(" ")[1]);
        countHard = Integer.parseInt(templateJSON.getTest().get(complexity.getName()).split(" ")[2]);

        ArrayList<Question> questionArrayListEasy = questionRepository.getByTopicAndComplexity(topic, ComplexityName.COMPLEXITY_EASY.toString());
        ArrayList<Question> questionArrayListMiddle = questionRepository.getByTopicAndComplexity(topic, ComplexityName.COMPLEXITY_EASY.toString());
        ArrayList<Question> questionArrayListHard = questionRepository.getByTopicAndComplexity(topic, ComplexityName.COMPLEXITY_EASY.toString());

        for (int i = 0; i < countEasy; i++) {
            randomIndex = rand.nextInt(questionArrayListEasy.size());
            questionDTOArrayList.add(converterJSON.parseJSON(questionArrayListEasy.get(randomIndex).getQuestionJSON()));
            questionArrayListEasy.remove(randomIndex);
        }
        for (int i = 0; i < countMiddle; i++) {
            randomIndex = rand.nextInt(questionArrayListMiddle.size());
            questionDTOArrayList.add(converterJSON.parseJSON(questionArrayListMiddle.get(randomIndex).getQuestionJSON()));
            questionArrayListMiddle.remove(randomIndex);
        }
        for (int i = 0; i < countHard; i++) {
            randomIndex = rand.nextInt(questionArrayListHard.size());
            questionDTOArrayList.add(converterJSON.parseJSON(questionArrayListHard.get(randomIndex).getQuestionJSON()));
            questionArrayListHard.remove(randomIndex);
        }

        Result result = new Result();
        result.setVariant(converterJSON.arrayConverterToJSON(questionDTOArrayList));
        result.setUser(user);
        result.setResult(null);

        return questionDTOArrayList;
    }

*/

}

