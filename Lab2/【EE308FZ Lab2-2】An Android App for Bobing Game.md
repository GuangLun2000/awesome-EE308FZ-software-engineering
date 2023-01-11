# 【EE308FZ Lab2-2】An Amazing Android App for Bobing Game 🎲

| EE308FZ Main Class                  | [Here](https://bbs.csdn.net/forums/MUEE308FZU202201)         |
| ----------------------------------- | ------------------------------------------------------------ |
| The Link of Requirement             | [Lab2 Requirement](https://bbs.csdn.net/topics/608859318)    |
| The Aim of this Lab2-               | Description of our Bobing Android App                        |
| My Student ID                       | 20122161_832002117                                           |
| Partner ID                          | 20124091_832002127                                           |
| Our Modao (Ink Knife, 墨刀原型设计) | [Our Modao Design Link](https://modao.cc/app/woSAp69rkrwldjgaYcM1) |
| Our Git Link                        | [Github](https://github.com/GuangLun2000/awesome-EE308FZ-software-engineering) or [Gitee](https://gitee.com/doubleq666/mid-autumn-festival-bo-cake) |
| Video Demo Link                     | [Our Demo Video!](https://www.bilibili.com/video/BV1w3411f7kE/?vd_source=7d823b93305117d6681e72d44764c57d) |
| Partner Blog Link                   | [Partner Link]                                               |

## Blog 2-2 Content

@[toc]



---

## (1) Introduction 👋🏻

The Mid-Autumn Festival is one of the traditional Chinese festivals, which symbolizes reunion and happiness. Bobing is a custom activity in southern Fujian. During the Mid-Autumn Festival, people get together with their relatives and friends to Bobing and enjoy the Mid-Autumn moon. 🎑 However, since the outbreak of COVID-19 in 2020, many people cannot gather together as before. In order to maintain the original holiday atmosphere, more and more people share the fun of Bobing through online Bobing application. 🎲

[In the case, our Android Bobing App is coming! 💥](https://www.bilibili.com/video/BV1w3411f7kE/?vd_source=7d823b93305117d6681e72d44764c57d)

<center>
  <img src = "https://img-community.csdnimg.cn/images/c4e6ba58cdf542e3bbca97a769274d6c.png" width = 50%>
</center>



---

## (2) Overview of Our Works 📮

In this Lab, an Android Bobing game application have been developed and suggested. This Bobing app is implenmented mainly using Java, mysql, javascript and Android Navigation. Experiments have been conducted to improve the gameplay of the proposed Bobing games. 🚀

The following figures show the [Prototype Design](https://modao.cc/app/woSAp69rkrwldjgaYcM1) of our UI and UX. And the latest version can be seen in our proposed [Android App Video](https://www.bilibili.com/video/BV1w3411f7kE/?vd_source=7d823b93305117d6681e72d44764c57d). 

<center>
  <img src = "https://img-blog.csdnimg.cn/a54fcfaea8634c209aeac31fd12b2ba6.png" width = 70%>
</center>




| **Grading standards**                     | **implementation**                                           |
| :---------------------------------------- | ------------------------------------------------------------ |
| Functional practicability(20)             | Finished. 👌                                                  |
| ---Single machine form                    | All the basic function have been implemented (100%). ✅       |
| ---Network connection,online from         | We used the Server to process the data and transfer the real-time ranking results to realize the function of multi-player pattern. Gernerally finished(80%). ✅ |
| Degree of innovation (5)                  | Our proposed Bobing App possess a Comprehensive Bobing rules to make the game as closer as possible to the real-world traditional Bobing. While we also realized a mobile shaking playing method to improve the playability of our game. ✅ |
| Degree of interface beautification (5)    | We are confident that our brilliant interface design is very advanced than others. Strongly finished. ✅ |
| Blog description (20)                     | Finished. 👌                                                  |
| Code specification (5)                    | Our coding specification can be seen [here.](https://github.com/GuangLun2000/awesome-EE308FZ-software-engineering/blob/main/my-code-specifications/Cpp_Code_Specifications.md) ✅ |
| Git commits information specification (5) | Yes, we utilized the Github and Gitee to collaborate. ✅      |



---

## (3) Design Methodology 🧑🏻‍🏫

**The following table shows the development technology we used in this lab2.**

| Application      | Technology                                         |
| :--------------- | :------------------------------------------------- |
| Front-End        | Android Navigation & okhttp & Javascript & Webview |
| Back-End         | Java & Javaweb & Spring Springmvc                  |
| Server Database  | mysql & jdbctemplate                               |
| Prototype Design | Modao (墨刀，Ink Knife)                            |
| UI Design        | Chuangketie (创客贴) & Adobe UI                    |
| Git Tool         | Github & Gitee (Chinese version)                   |

**The following flow-charts illustrate the design methodology of our works.**

1. The User strats the Bobing Game App;
2. User clicks to enter next page;
3. User fills in the infomation of ID;
4. Game playing & Data Transfer to Server;
5. Server process the data;
6. Server transfer the data back to the Ranking List (HTML5);
7. User stops the game (over).

<center>
  <img src = "https://img-community.csdnimg.cn/images/a8aeba49d290431790b71938449cd55b.png"
       width = 25%>
  <br>
  Flow Chart of Our Bobing App
</center>




<center>
  <img src = "https://img-blog.csdnimg.cn/987ef015a7b942dc85a46d044d3b4fe4.png" width = 100%>
</center>



---

## (4) 3 events that take a long time 🕶️

1. First of foremost, the biggest challenge during our works was to learn to communicate and collaborate with teammates effectively. We may possessed differences and conflicts, which require close communication and cooperation. And it really gives us a sense of accomplishment to finish the challenging development of this application.

2. Then, how to realized the online gaming experience is difficult for us. We have learned the relavant knowledge from 0 to 1. Finally, we resolved this part of works. We used the Server to process the data and transfer the real-time ranking results to realize the function of multi-player pattern. The related codes to realize this function have been shown as follow.

```java
if (now <= 0) {
                        sensorManager.unregisterListener(sensorEventListener);

                        paiming.setVisibility(View.VISIBLE);

                        OkHttpClient client = new OkHttpClient.Builder().build();
//                            Request request = new Request.Builder().url("http://10.0.2.2:8080/Bobing_war_exploded/submit-rank?nickname=demo&score=2000&time=0.66666").build();
                        Request request = new Request.Builder().url("https://www.doubleq666.cn/bobing/submit-rank?nickname=" + nickname + "&score=" + cnt).build();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Response response = client.newCall(request).execute();
                                    Log.d("OkHttp", response.body().string());
                                } catch (IOException e) {
                                    Log.d("miaomiao", "failed");
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
```

3. Also, the implement of realizing the connection between our Andriod App with the Server was a big obstruction. We have followed the related guidance in the CSDN, and address the implement of this function. The related codes to realize this function have been shown as follow.

```java
@WebServlet(name = "submit-rank", value = "/submit-rank")
public class SubmitRank extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        request.setCharacterEncoding("utf-8");

        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
            String sql = "insert into bobing.score_xing values(null, ?, ?)";
            jdbcTemplate.update(sql, map.get("nickname")[0], map.get("score")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
```

---

## (5) Pair programming experience 👨🏻‍💻+👨🏻‍💻

The following figures show the pair working of our team, and the git commits during our works.

<center>
  <img src = "https://img-blog.csdnimg.cn/7db2c6bbf0994d38a7386e08e1cdc3af.jpeg" width = 60%>
  <br>
  Hanlin CAI and Qiguo Qing.
</center>



<center>
  <img src = "https://img-community.csdnimg.cn/images/0147dd874b32436aafb79fece8d344ed.png" width = 60%>
  <br>
  The Gitee commits (part of our works)
</center>



---

## (6) The demo video of our Android App 🎥

[Here is the demo of our Android Bobing App. (Click) 🔗](https://www.bilibili.com/video/BV1w3411f7kE/?vd_source=7d823b93305117d6681e72d44764c57d)

*Final update in 16:56pm 18th Nov.*

---

## (6) PSP Table of this works 📝

| PSP TABLE                              | Estimated Time Consumption (mins) | Completed Time(mins) |
| -------------------------------------- | --------------------------------- | -------------------- |
| **Planning**                           | ---                               | ---                  |
| ·Estimate                              | 60                                | 60                   |
| **Development**                        | ---                               | ---                  |
| ·Analysis                              | 40                                | 30                   |
| ·Design Spec                           | 30                                | 30                   |
| ·Design Review                         | 40                                | 40                   |
| ·Coding Standard                       | 10                                | 5                    |
| ·Design                                | 60                                | 80                   |
| ·Coding                                | 600                               | 720                  |
| ·Coding Review                         | 120                               | 60                   |
| ·Test                                  | 30                                | 40                   |
| **Reporting**                          | ---                               | ---                  |
| ·Test Report                           | 30                                | 20                   |
| ·Size Measurement                      | 10                                | 15                   |
| ·Postmortem & Process Improvement Plan | 30                                | 40                   |
| **TOTAL**                              | 1060                              | 1140                 |

---

## (7) Summary for Lab 2 ✅

In this project, we constrcted an Android App for Traditional Bobing game.

1. In Lab 2-1, we have bulit the Prototype of our Android APP utlizing Modao (Ink Knife) and Chuangketie (for UI design).
2. In Lab 2-2, we have implemented the proposed Android Bobing Game Application using various development techonology (as shown in the table above).

The developing experience of this Bobing Game App greatly improve our abilities to utilize what we have learned in lectures to real-world practice. And we have gained a lot during this Lab2 experience.

---

## (8) Acknowledgements 🥰

I hope to express my sincere thanks to Lecturer. Lin for his generous guidance. And I would like to thanks to our tutors for their patient examination and evaluation.

Finally, thanks to my partner, who is an brilliant student-developer, for his enthusiasm and hard-work. 🥳

---

## Blog Statement 🚀

<img src="https://img-blog.csdnimg.cn/6340ffe160c54bcc9dc0e9255516b7c3.jpeg#pic_center =500x300" alt="请添加图片描述" style="zoom:50%;" />

| EE308FZ Main Class              | [Here](https://bbs.csdn.net/forums/MUEE308FZU202201) |
| ------------------------------- | ---------------------------------------------------- |
| My Academic Website             | [Hanlin CAI](https://caihanlin.com/)                 |
| My MU Student ID                | 20122161                                             |
| My FZU Student ID               | 832002117                                            |
| If you want to contact with me? | hanlin.cai@ieee.org                                  |
