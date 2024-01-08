## General

[Apache Groovy](https://groovy-lang.org/documentation.html)  
[Jenkins on Azure documentation](https://learn.microsoft.com/en-us/azure/developer/jenkins/)  

## Pluralsight

[Getting Started with Jenkins](https://app.pluralsight.com/library/courses/getting-started-jenkins/table-of-contents)  
[Running Jenkins in Docker](https://app.pluralsight.com/library/courses/running-jenkins-docker/table-of-contents)  
[Automating Jenkins with Groovy](https://app.pluralsight.com/library/courses/automating-jenkins-groovy/table-of-contents)  

---

[Jenkins + docker-compose Makes It Incredibly Easy to Run Instances Side by Side on the Same Host](https://app.pluralsight.com/course-player?clipId=8d1e075c-a831-4e54-9d98-6594e8a4ddbc)

```
docker-compose up
```

[Intro to Docker - A Tool Every Developer Should Know](https://www.youtube.com/watch?v=WcQ3-M4-jik&t=2329s)  
[The Best Way To Use Docker For Integration Testing In .NET](https://www.youtube.com/watch?v=tj5ZCtvgXKY)  

[Unit Testing T-SQL Code with tSQLt](https://app.pluralsight.com/library/courses/unit-testing-t-sql-tsqlt/table-of-contents)    

--- 

[DockerHub-Jenkins Continuous Integration and Delivery server.](https://hub.docker.com/r/jenkins/jenkins)  
[Official Jenkins Docker image-GitHub](https://github.com/jenkinsci/docker/blob/master/README.md)  


--- 

[Jenkins](https://www.jenkins.io/)     
[Using Jenkins agents](https://www.jenkins.io/doc/book/using/using-agents/)  
[CloudBeesTV](https://www.youtube.com/@CloudBeesTV/playlists)  
[Jenkins+Docker by CloudBees](https://www.youtube.com/watch?v=BexOKevEVa0&list=PLvBBnHmZuNQLcH9pn-fmpKBoX_L0kvNAx)   

---

## Why would one want to use Docker with Jenkins?

### 1. The Developer Perspective

[Docker Rocks for Learning Jenkins Thanks to the Official jenkins/jenkins Images - Setup is Effortless](https://app.pluralsight.com/course-player?clipId=68ef696f-a498-49dd-b951-4b116e744f25))
[Using docker-compose up to Spin up Jenkins and a MailHog Test Email Server!](https://app.pluralsight.com/course-player?clipId=716dc35e-1797-4bb9-b160-1812b2bf878f)   
[Clean up and Recreation Is a Breeze with docker-compose](https://app.pluralsight.com/course-player?clipId=2d290806-ad22-43d8-b21a-7ca42da2d02c)  

### 2. The DevOps Perspective

[The Vision and the Why: Jenkins on Docker](https://app.pluralsight.com/course-player?clipId=ba6e5004-8ca3-4ea6-a6e7-5f05477018b0)   

In the classic **Main Controller Configuraion** of Jenkins you have a **Jenkins Controller** and at least one **Agent (aka) Node**.
However, normally in a real contests it is desireble to set up Jenkins so that there is a **Jenkins Controller** and as many Agents
as possible to make aptimal use of the available underlying resources and to minimize the DevOps cycle. 
In summary therefore there si a tewofold goal.

1. make aptimal use of the available underlying resources
2. minimize the DevOps cycle. 

In a traditional contest one would operate a build pipeline on **Virtual Machines**. 
In the simplest of cases you may have a single VM where you install the **Jenkins Controller** and then one or more **Jenkins Agents**.
In more sofisticated scenario the **Jenkins Agents** would spill over additional VMs as the demand of the pipeline grows over time. 

#### The Traditional Jenkins setup  

With this model there are two competing effects at play. 

 1. In order to achieve reasonable performance, one would have a dedicated VM for each installed **Jenkins Agents**
 2. it is possible to install multiples **Jenkins Agents** on the same VM which increases parallelization over a set of constraint resources.

#### The Jenkins setup with Containers 

This model is intrinsicly far more inefficient than running **Jenkins Agents** as containers on an equal set of VMs.

However, each VM has its own Kernel therefore once it is up and running it keeps running whether it is idle or not.
This is intrinsicly far more inefficient than running **Jenkins Agents** as containers on an equal set of VMs because with containers 
there is only one **shared Kernal** borrowed from the **Host OS** and no additionla resources are booked to the exclusive use of the
agent

---

[Demo: The Basics of Running Jenkins in a Container](https://app.pluralsight.com/course-player?clipId=238f1b69-fa8f-4cec-b22f-b36cdffeb3a5)   
[Demo: The Basics of Running Jenkins in a Container](https://app.pluralsight.com/course-player?clipId=fab899b5-00c9-4814-b16f-5ca9b0ac6036) 

The image jenkins is a weekly release while the jenkins/jenkins:lts is the 4-months stable version.

```
docker image list
docker search jenkins 
docker pull jenkins/jenkins:lts
docker run -p 8081:8080 jenkins/jenkins:lts
docker ps -a
```

Use the initial admin password taht the container prints out on the shell and use it @ localhost:8080 where the **Jenkins Startup Page** 
for this running container is located. Notice that there is no additional setup required i.e. no JAVA_HOME or JDK installed, it is all
packaged up into the Docker Container.

```
docker run -p 50000:8080 -name jenkins-master jenkins/jenkins:lts 
docker exec -it jenkins-master /bin/bash
cd var/jenkins_home
ls
cd jobs
cd ../secrets
cat initialAdminPassword
```
---

[Maintaining State Outside the Container](https://app.pluralsight.com/course-player?clipId=2c794ca0-31a0-486a-98a2-50d0631b74d0)  

We have two aspects to consider on this point.

1. The 

Jenkins offers two foundamental ways to manage the definition of builds and its setup.
By default Jenkins maintains state as a set of files **on disc** located in the folder that is set up as value of the 
path variable `JENKINS_HOME`, the default should be `user.home/.jenkins` and in this folder (directory) the file
`config.xml` is also located that contains the configuration for Jenkins together with either files that are used to 
maintain the definition of the builds.

In thismode of operation it is advisable to regularly **back up** the contents of the directory `JENKINS_HOME` in 
order to preserve the state of the Jenkins setup together with the definition of teh builds.

More information is available at the link below.

[Spelunking JENKINS_HOME: How to Reset Your Jenkins Install and Back It Up](https://app.pluralsight.com/course-player?clipId=921b3f28-2314-4710-8127-272c70b5142e) 

---

#### Freestyle Projects (aka the Toilet Paper Form) vs. Pipeline Type Project (Scripted Pipeline Syntax)

- [Reflecting on Freestyle Projects Aka the Toilet Paper Form](https://app.pluralsight.com/course-player?clipId=619bd731-9d4d-478e-9122-bf6756c046a6)  
- [Jobs Are Backed by XML Config Files](https://app.pluralsight.com/course-player?clipId=4c1e98fb-f62d-4f3b-96ea-a69db2b74f57)  
- [Changing config.xml on Disk Then Reloading Configuration from Disk](https://app.pluralsight.com/course-player?clipId=759da612-7b13-465e-8028-71e22a6aca94)  
- [Pipeline Jobs Also Have a config.xml](https://app.pluralsight.com/library/courses/getting-started-jenkins/table-of-contents)   
- [Git Clone in a Declarative Pipeline](https://app.pluralsight.com/course-player?clipId=3aea9dcd-d1ec-4432-ad77-0c0bdbbd77a9)   

---

### Maintaining State Outside the Container - Pipeline Scripts and Jenking Files

[Maintaining State Outside the Container](https://app.pluralsight.com/course-player?clipId=2c794ca0-31a0-486a-98a2-50d0631b74d0)

#### Jenkins Classic Builds

By default Jenkins maintains its setup state and the state of the build definitions by storing files on disc.
The **config.xml** under the **JOB directory on the Jenkings file system** on disc is where Jenkings stores the
information about **the build configuration** and the **steps configurations**. This is the case also when Jekings
is used within a Docker  container, as an istallation on OS or on teh OS of a VM.

If you can replace this operation mode with Pipeline Scripts in version control ASAP!
 
#### Keep your builds in version control with Pipeline Scripts!

**Pipeline Scripts and Jenking Files** are a way to store and maintain this state **outside of the container** by 
using **version control**. These files then can the bue **pulleed dynamically as the first step of your build**.
While **build history, plugins and dependencies can be rebuilt**, the **build definitions** are the most important 
assets and sotring then in version control is the best course of action. 

#### Store Jenkins Configurations in a Mount Volume for a basic Docker Image

[The Docker File System](https://app.pluralsight.com/course-player?clipId=c4e4b89a-18ec-4b5f-9344-8a7d38f970ad) 
[Understanding Copy on Write](https://app.pluralsight.com/course-player?clipId=c4e4b89a-18ec-4b5f-9344-8a7d38f970ad)
[Demo: Mounting a Volume to Your Container](https://app.pluralsight.com/course-player?clipId=11238ab2-8967-4fd4-8393-6b4b7bfc055a)

```
docker run -2119:8080 -p 50000:5000 -v C://Docker/Volumes/jenkins-master:var/jenkins/jenkins_home -name jenkins-master jenkins/jenkins:lts

docker rm jenkins-master
```

---


### Jenkins Main Controller Configuration and Agent Nodes

[Adding a Jenkins Agent Node](https://www.pluralsight.com/resources/blog/cloud/adding-a-jenkins-agent-node)

Any type of machine that can run Java can be an **Agent** for a **Jenkins Controller**.

#### Q-1

Why do I need an **Agent** ? 
Can't I simply run Jenkins directly on the **Jenkins Controller**?

#### A-1

The answer is yes you can but you should not!
Use the **Main Controller Configuraion** instead.

---

[Jenkns: Pipeline as Code](https://www.jenkins.io/doc/book/pipeline-as-code/)

To use Pipeline as Code, projects must contain a file named Jenkinsfile in the repository root, which contains a "Pipeline script."

[Building a Modern CI/CD Pipeline with Jenkins](https://app.pluralsight.com/library/courses/building-modern-ci-cd-pipeline-jenkins/table-of-contents)   

https://github.com/devbyaccident/azure-voting-app-redis 
https://github.com/devbyaccident/demo-shared-pipeline

https://www.jenkins.io/doc/book/pipeline/
https://www.jenkins.io/doc/book/pipeline/syntax/
https://www.jenkins.io/doc/pipeline/steps/

[Connecting Jenkins To GitHub](https://app.pluralsight.com/course-player?clipId=f40e709c-ef20-41e4-bd02-b8203021e380)     

[Running Scripted Pipeline](https://app.pluralsight.com/course-player?clipId=57a92ff5-273a-4d4e-85c4-bae6dbdbf8c1)    

[Running Scripted Pipeline from SCM](https://app.pluralsight.com/course-player?clipId=bc90e0f5-0e21-49ec-9985-202143e60547)    

[Configuring a Multi-Branch Pipeline](https://app.pluralsight.com/course-player?clipId=78433521-f99b-4d02-8664-97832205d1d6)  

--- 

## The Problem of the versioning Lag and the Upgrade Anti-Pattern

[Upgrading Jenkins](https://app.pluralsight.com/course-player?clipId=0fa608b6-4b83-47aa-b500-f5cd45728530)  

In the traditional install of Jenkins or similar CI tools there is the problem of version lag
as it is unlikely that the DevOps Team can tale pace with the rate of delivery of new versions
of the CI brick. This normally results that the lag becomes large and it is closed only when
it becomes absolutely necessary at possibly a huge cost and risk.

This is actually one of the problems that Docket+Jenkins solves. In general terms, one may 
make a case of the following well-known DevOps statement.

```
If something hurts, do it often!
```

This is to say that in proper DevOps one should strive to automate those operations that are 
tedious and can potentially and factually cause huge costs or situations of stress which may
detract resources from the business goals of the organization.

```
If something hurts, do it often, faster cheaper and better!
```

[Upgrading Plug-ins](https://app.pluralsight.com/course-player?clipId=b4060b24-c595-47cc-9fc0-d4000e8197d8)

When the Jenkins LTS image is upgraded also all the **included** plugins are upgraded, unless
the Jenkins upgrade engine detects that any plug-in has been manually, in which case it will
leave those alone.

If you need to upgrade also the previously manually upgraded plug-ins as part of the upgrade of 
the LTS then there are two options.

 1. Perform the manual upgrade for thsoe plugins
 2. set PLUGINS_FORCE_UPGRADE

Option 1. Perform the manual upgrade for those plugins is complicated as the latest version of the
plugin may not be the version of the plugin tha is compatible with teh LTS image. In this cas it is
necessary to verify manually which version of teh plugins correspond to the LTS version of Jenkins.

Option 2. 

```
docker run -p 2119:8080 -p 50000:50000
-e PLUGINS_FORCE_UPGRADE=true
-v c://Docker/Volumes/jenkins-master:/var/jenkins_home
-name jenkins-master jenkins/jenkins:lts
```






--- 

 # Jenkins and Azure DevOps 

[Jenkins on Azure documentation](https://learn.microsoft.com/en-us/azure/developer/jenkins/)  
[Continuously deploy from a Jenkins build](https://learn.microsoft.com/en-us/azure/devops/pipelines/release/integrate-jenkins-pipelines-cicd?view=azure-devops&tabs=yaml)   
[Tutorial: Use Azure Container Instances as a Jenkins build agent](https://learn.microsoft.com/en-us/azure/developer/jenkins/azure-container-instances-as-jenkins-build-agent)  
[Get Started: Install Jenkins on an Azure Linux VM](https://learn.microsoft.com/en-us/azure/developer/jenkins/configure-on-linux-vm)   

---

## [Automating Jenkins with Groovy](https://app.pluralsight.com/library/courses/jenkins-groovy-automating/table-of-contents)

[The Groovy Console](https://app.pluralsight.com/course-player?clipId=2a284fe6-3654-45a9-aac5-7286b69d7cbd)
[Ways to get Apache Groovy}(http://groovy-lang.org/download.html)

```
cd 'C:\VSProjects\MyProjetcs\Groovy\apache-groovy-sdk-4.0.14\groovy-4.0.14\bin'
groovyconsole
```

---

What are the advantages of running Jenkins in a docker container
https://stackoverflow.com/questions/44440164/what-are-the-advantages-of-running-jenkins-in-a-docker-container 

1) I want most of the configuration for the server to be under version control.

2) I want the ability to run the build server locally on my machine when I’m experimenting with new features or configurations

3) I want to easily be able to set up a build server in a new environment (e.g. on a local server, or in a cloud environment such as AWS)



----

[Is Docker Still Relevant?](https://www.youtube.com/watch?v=Cs2j-Rjqg94)  

- runtime-spec
- image-spec
- distribution-spec

[Open Container Initiative](https://opencontainers.org/)  
The Open Container Initiative is an open governance structure for the express purpose of creating open industry standards around container formats and runtimes.


[Tutorial: Use Azure Container Instances as a Jenkins build agent](https://learn.microsoft.com/en-us/azure/developer/jenkins/azure-container-instances-as-jenkins-build-agent)  


Azure Container Instances (ACI) provides an on-demand, burstable, and isolated environment 
for running containerized workloads. Because of these attributes, ACI makes a great platform
for running Jenkins build jobs at a large scale. 

This article shows you how to deploy an ACI and add it as a permanent build agent for a Jenkins 
controller.

---

[Deploy an ASP.NET Core (Docker) application to Kubernetes using Octopus, Jenkins, and Docker Registry](https://octopus.com/docs/guides/deploy-aspnetcore-docker-app/to-k8s/using-octopus-onprem-jenkins-dockerhub)  



https://github.com/FeynmanFan 
https://github.com/FeynmanFan/JenkinsGroovy 


---

## Controller and Agent Node Configuration

[How to Create an Agent Node in Jenkins](https://www.youtube.com/watch?v=99DddJiH7lM)  
[Create a new Jenkins node, and run your Jenkins agent as a service ](https://www.jenkins.io/blog/2022/12/27/run-jenkins-agent-as-a-service/)  
---