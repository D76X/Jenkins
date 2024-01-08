
https://github.com/g0t4/course-jenkins-getting-started 


---

---

## TOPIC #1: Some basics about Docker and Docker Containers


[Overview of the get started guide](https://docs.docker.com/get-started/)

Running Jenkins in Docker
[The Docker File System](https://app.pluralsight.com/ilx/video-courses/11cf4ce8-1747-4679-969e-83becb6fefd3/ff6d8c86-ed91-4258-b2a9-f90920a0db08/52145344-7e85-4f49-b3de-958a2c846252)    
[Understanding Copy on Write](https://app.pluralsight.com/ilx/video-courses/11cf4ce8-1747-4679-969e-83becb6fefd3/ff6d8c86-ed91-4258-b2a9-f90920a0db08/5c5fc4b4-f4ba-4514-959f-c1624839e414)  

- talk about the TWL: Top Writable Layer
  The TWL is in effect the state of a running container!
  This is what distiguishes from its image!
  Take care of your container files: back-up / mirror / RAID / use Docker volumes...

--- 

## TOPIC #2: The Problem of the state

#### Getting Started with Jenkins

[Freestyle, Pipeline, Jenkinsfile](https://app.pluralsight.com/ilx/video-courses/48cf7494-f57b-4f79-90a3-fce0df92fa7a/f6f67fca-ef22-4137-a82a-20ab45d9622c/3827ba49-d575-4ff9-b5a0-903e2afc6a87)   
[Develop Jenkins Pipelines in VSCode](https://app.pluralsight.com/ilx/video-courses/48cf7494-f57b-4f79-90a3-fce0df92fa7a/f6f67fca-ef22-4137-a82a-20ab45d9622c/757a2b77-e337-4e7a-9288-cfff8a89970c)    

In this part you mentione that with Jenkins we have multiple ways to define a **Workflow**.

1. As a Freestyle Workflow
2. As a Pipeline Workflow with embedded Groovy Script
3. As a Pipeline Workflow with the Groovy Script saved on a Code Repository

Running Jenkins in Docker
[Maintainig State Outside the Container](https://app.pluralsight.com/ilx/video-courses/11cf4ce8-1747-4679-969e-83becb6fefd3/ff6d8c86-ed91-4258-b2a9-f90920a0db08/965c3e1f-0528-43dd-85b2-c336207788bb) 

Strive to keep state outside of the container:
1. Store the Build Definition in Version Control as Pipeline Scripts rather than with the Classic Pipeline Scripts as the latter is a XML file tied to the Filesystem.

---

---

## TOPIC #3: Why Jenkins in Docker?

[Using Docker with Pipeline ](https://www.jenkins.io/doc/book/pipeline/docker/)  

  1. to unify their build and test environments across machines.
 
  2. a user can define the tools required for their Pipeline, without having to manually configure agents.
    Any tool that can be packaged in a Docker container can be used with ease, by making only minor edits 
    to a Jenkinsfile.
    
  3. Using multiple containers thus multiple build toolsets
    For example, a repository might have both a Java-based back-end API implementation and a 
    JavaScript-based front-end implementation. Combining Docker and Pipeline allows a Jenkinsfile 
    to use multiple types of technologies, by combining the agent {} directive with different stages.

  It has become increasingly common for code bases to rely on multiple different technologies. 
 
  4. to provide an efficient mechanism for deploying applications.

# 2.[Customizing the execution environment](https://www.jenkins.io/doc/book/pipeline/docker/#execution-environment)

The Pipeline is designed to easily use Docker images as the execution environment for a single Stage or the entire Pipeline. 
Users can define the tools required for their Pipeline, without having to manually configure agents. 
Any tool that can be packaged in a Docker container can be used with ease, by making only minor edits to a Jenkinsfile.
When the Pipeline executes, Jenkins will automatically start the specified container and execute the defined steps within.

---

  1. try to run containers side to side 
  2. You want ot test a new feature that has been recently release to Jenkins and you would like to do so on your current pipeline definition.
  3. With Docker you can spin up additional isolated environments on the same machine!
  4. Just define the two running containers on separate ports!

Getting Started with Jenkins
[Using docker-compose with Jenkins](https://app.pluralsight.com/ilx/video-courses/48cf7494-f57b-4f79-90a3-fce0df92fa7a/b6f9cf19-86a1-4b55-96a4-edf86e33c912/716dc35e-1797-4bb9-b160-1812b2bf878f)
[Jenkins + docker-compose Makes It Incredibly Easy to Run Instances Side by Side on the Same Host](https://app.pluralsight.com/ilx/video-courses/48cf7494-f57b-4f79-90a3-fce0df92fa7a/806d7c4c-b5e8-4076-9384-15b3842f3290/8d1e075c-a831-4e54-9d98-6594e8a4ddbc)   
[Enabling the Dark Theme](https://app.pluralsight.com/ilx/video-courses/48cf7494-f57b-4f79-90a3-fce0df92fa7a/806d7c4c-b5e8-4076-9384-15b3842f3290/4a510bf1-48a2-4b8b-b235-bec32e5e2592)   

### Commands
```
docker-compose up
docker-compose down
```

---

## TOPIC #4: How Jenkins in Docker?

## How to run Jenkins in a Docker Container on a development machine.

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

### Install tools on a Docker Image.

## Install .Net SDK on a Linux Distribution Docker Image.

- [adding .net core to docker container with Jenkins](https://stackoverflow.com/questions/48104954/adding-net-core-to-docker-container-with-jenkins)  

In the following script we have replaced `dotnet-sdk-2.0.0` with `dotnet-sdk-6.0.0` in order to install
**.NET 6.0**. This can be updated to install any future version of .Net SDK.
The .Net installed on the **Jenkins Controller Node** as well as any **Agen Node** that may be used
to build **.Net** source code.

```
FROM jenkins/jenkins:lts
 # Switch to root to install .NET Core SDK
USER root

# Just for my sanity... Show me this distro information!
RUN uname -a && cat /etc/*release

# Based on instructiions at https://learn.microsoft.com/en-us/dotnet/core/linux-prerequisites?tabs=netcore2x
# Install depency for dotnet core 2.
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
    curl libunwind8 gettext apt-transport-https && \
    curl https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.gpg && \
    mv microsoft.gpg /etc/apt/trusted.gpg.d/microsoft.gpg && \
    sh -c 'echo "deb [arch=amd64] https://packages.microsoft.com/repos/microsoft-debian-stretch-prod stretch main" > /etc/apt/sources.list.d/dotnetdev.list' && \
    apt-get update

# Install the .Net Core framework, set the path, and show the version of core installed.
RUN apt-get install -y dotnet-sdk-6.0.0 && \
    export PATH=$PATH:$HOME/dotnet && \
    dotnet --version

# Good idea to switch back to the jenkins user.
USER jenkins
```

The `uname -a && cat /etc/*release` which is embedded in this exerpt of Dockerfile can be used
in the **Exec** pane of **Docker Client** or terminal to verify the Linux Distribution the 
image is based on. 
The image used as **Jenkins Controller** is **jenkins/jenkins:lts** and the following is the 
 output the Developer machine. It is easy to see that it is a **Debian** distribution for **WSL2**.

```
Linux daf6fd2fe971 5.10.102.1-microsoft-standard-WSL2 #1 SMP Wed Mar 2 00:30:59 UTC 2022 x86_64 GNU/Linux
PRETTY_NAME="Debian GNU/Linux 12 (bookworm)"
NAME="Debian GNU/Linux"
VERSION_ID="12"
VERSION="12 (bookworm)"
VERSION_CODENAME=bookworm
ID=debian
HOME_URL="https://www.debian.org/"
SUPPORT_URL="https://www.debian.org/support"
BUG_REPORT_URL="https://bugs.debian.org/"
$
```
---

### Jenkins Plugis that must be installed

[Dashboard > Manage Jenkins > Plugins](http://localhost:8081/manage/pluginManager/)  

On the **Jenkins Controller Node** or an **Jenkins Agent Node** the following **Jenkins Plugins**
mat need to be installed.

1. [.NET SDK Support](https://plugins.jenkins.io/dotnet-sdk/)

This is required to...


2. [Docker Pipeline](https://plugins.jenkins.io/docker-workflow/)

This is required to...

---

### Installing Docker on the machine of a Jenkins node

[Downloading and running Jenkins in Docker](https://www.jenkins.io/doc/book/installing/docker/#downloading-and-running-jenkins-in-docker)    

Use the recommended officialimage from the Docker Hub repository: 

- [jenkins/jenkins](https://hub.docker.com/r/jenkins/jenkins) 
- [Official Jenkins Docker image - GitHub](https://github.com/jenkinsci/docker/blob/master/README.md)  

This image contains the current Long-Term Support (LTS) release of Jenkins, 
which is production-ready. However, this image **doesn’t contain Docker CLI**, 
and is not bundled with the frequently used Blue Ocean plugins and its features. 
To use the full power of Jenkins and Docker, you may want to go through the 
installation process described below.

[On macOS and Linux](https://www.jenkins.io/doc/book/installing/docker/#on-windows)  
...

[On Windows](https://www.jenkins.io/doc/book/installing/docker/#on-windows)  

The Jenkins project provides a Linux container image, not a Windows container image.
Be sure that your Docker for Windows installation is configured to run Linux Containers 
rather than Windows Containers. 

Refer to the Docker documentation for instructions to switch to Linux containers.
https://docs.docker.com/desktop/get-started/#switch-between-windows-and-linux-containers 

Once configured to run Linux Containers, the steps are:

1. Create a bridge network in Docker
In a terminal: `docker network create jenkins`

---

# Docker in Dockler

## Docker-in-Docker vs Docker-out-of-Docker

[Docker-in-Docker vs Docker-out-of-Docker](https://tdongsi.github.io/blog/2017/04/23/docker-out-of-docker/)    
[How To Run Docker in Docker](https://shisho.dev/blog/posts/docker-in-docker/)    

#### Docker-out-of-Docker specifics

[Docker Tips : about /var/run/docker.sock](https://betterprogramming.pub/about-var-run-docker-sock-3bfd276e12fd)  
[var/run/docker.sock](https://www.educative.io/answers/var-run-dockersock)  


#### Other Articles Related to Docker-in-Docker

[Docker in Docker](https://hub.docker.com/_/docker)  
[~jpetazzo/Using Docker-in-Docker for your CI or testing environment? Think twice.](https://jpetazzo.github.io/2015/09/03/do-not-use-docker-in-docker-for-ci/)  
[docker run docker (redux)](https://asciinema.org/a/378669)  

---

## [Running Docker in Docker on Windows (Linux containers) also for Jenkins CI](https://cloudcasanova.com/running-docker-in-docker-on-windows/)    

> This Article explains well all issues realted to hetting

```
docker run --name jenkins-blueocean --restart=on-failure --detach ^
--group-add 0 ^
--volume //var/run/docker.sock:/var/run/docker.sock ^
--volume jenkins-data:/var/jenkins_home ^
--publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1 
```

### Related Articles

[Docker Docs - Docker run - Additional groups](https://docs.docker.com/engine/reference/run/#additional-groups)  
[How to fix docker: Got permission denied while trying to connect to the Docker daemon socket](https://www.digitalocean.com/community/questions/how-to-fix-docker-got-permission-denied-while-trying-to-connect-to-the-docker-daemon-socket)   
[Docker: Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock](https://stackoverflow.com/questions/47854463/docker-got-permission-denied-while-trying-to-connect-to-the-docker-daemon-socke)  

---

2. Run a docker:dind Docker image
In a Cmd Terminal (BASH):

????? this does not even start!
```
docker run --name jenkins-docker --rm --detach ^
  --privileged --network jenkins --network-alias docker ^
  --env DOCKER_TLS_CERTDIR=/certs ^
  --volume jenkins-docker-certs:/certs/client ^
  --volume jenkins-data:/var/jenkins_home ^
  --publish 2376:2376 ^
  docker:dind
```

3. Build a new docker image from this Dockerfile and assign the image a meaningful name, e.g. "myjenkins-blueocean:2.426.1-1":
`docker build -t myjenkins-blueocean:2.426.1-1 .`

If you have not yet downloaded the official Jenkins Docker image, the above process automatically downloads it for you.

4. Run your own myjenkins-blueocean:2.426.1-1 image as a container in Docker using the following docker run command:

```
docker run --name jenkins-blueocean --restart=on-failure --detach ^
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 ^
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 ^
  --volume jenkins-data:/var/jenkins_home ^
  --volume jenkins-docker-certs:/certs/client:ro ^
  --publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1
```

```
docker run --name jenkins-blueocean --restart=on-failure --detach ^
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 ^
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 ^
  --volume jenkins-data:/var/jenkins_home ^
  --volume jenkins-docker-certs:/certs/client:ro ^
  --publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1 ^
  docker:dind
```

```
docker run --name jenkins-blueocean --restart=on-failure --detach --publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1     
```


```
docker run --name jenkins-blueocean --restart=on-failure --detach \
--network jenkins --env DOCKER_HOST=tcp://docker:2376 \
--volume /var/run/docker.sock:/var/run/docker.sock \
--volume jenkins-data:/var/jenkins_home \
--volume jenkins-docker-certs:/certs/client:ro \
--publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1 
```

```
docker run --name jenkins-blueocean --restart=on-failure --detach \
-v /var/run/docker.sock:/var/run/docker.sock \
--publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1 
```

```
docker run --name jenkins-blueocean --restart=on-failure --detach ^
-v /var/run/docker.sock:/var/run/docker.sock ^
--publish 8082:8080 --publish 50002:50000 myjenkins-blueocean:2.426.1-1 
```

---

### Problem

```
error during connect: Get "https://docker:2376/v1.24/images/json": dial tcp: lookup docker on 127.0.0.11:53: server misbehaving
```


[Jenkins failing to connect to docker while building](https://community.jenkins.io/t/jenkins-failing-to-connect-to-docker-while-building/6936/2)  

[Error from server: error dialing backend: dial tcp: lookup worker-1 on 127.0.0.53:53: server misbehaving](https://github.com/kelseyhightower/kubernetes-the-hard-way/issues/630)  

script.sh: docker: not found #962
https://github.com/jenkinsci/docker/issues/962 


[No such host error in Jenkins pipeline when pushing docker image to unauthorized private registry](https://community.jenkins.io/t/no-such-host-error-in-jenkins-pipeline-when-pushing-docker-image-to-unauthorized-private-registry/962)

[Docker repository server gave HTTP response to HTTPS client](https://stackoverflow.com/questions/49674004/docker-repository-server-gave-http-response-to-https-client/68733536#68733536)

https://stackoverflow.com/questions/42211380/add-insecure-registry-to-docker

---

# The Problem of Connecting a Docker Agent to a Docker Controller


[Running Jenkins Controller locally with Docker](https://benmatselby.dev/post/jenkins-basics/)  
[Running Jenkins Agent locally with Docker](https://benmatselby.dev/post/jenkins-basic-agent/)  
[jenkins/inbound-agent](https://hub.docker.com/r/jenkins/inbound-agent)

---

### Docker Network

[https://stackoverflow.com/questions/43904562/docker-how-to-find-the-network-my-container-is-in](https://stackoverflow.com/questions/43904562/docker-how-to-find-the-network-my-container-is-in)  
[https://maximorlov.com/4-reasons-why-your-docker-containers-cant-talk-to-each-other/#do-the-containers-share-a-network](https://maximorlov.com/4-reasons-why-your-docker-containers-cant-talk-to-each-other/)  

```
 docker inspect jenkins-blueocean -f "{{json .NetworkSettings.Networks }}"
 docker inspect -f '{{range $key, $value := .NetworkSettings.Networks}}{{$key}} {{end}}' jenkins-blueocean
```

```
 docker run --rm `
 -eJENKINS_SECRET=58731f8abec31462273b3db8ccd13a54fb9fb61cb4350fa97aaa0eb42826c551 `
 -eJENKINS_URL=http://jenkins-blueocean:8082 `
 -eJENKINS_AGENT_NAME=agent1 `
 --network jenkins `
 --init `
 -it `
 jenkins/inbound-agent

  docker run --rm `
 -eJENKINS_SECRET=58731f8abec31462273b3db8ccd13a54fb9fb61cb4350fa97aaa0eb42826c551 `
 -eJENKINS_URL=http://jenkins-blueocean:8082 `
 -eJENKINS_AGENT_NAME=agent1 `
 -eJENKINS_WEB_SOCKET=true `
  --init `
 jenkins/inbound-agent
```

```
 docker run --rm `
 -eJENKINS_SECRET=58731f8abec31462273b3db8ccd13a54fb9fb61cb4350fa97aaa0eb42826c551 `
 -eJENKINS_URL=http://jenkins-blueocean:8082 `
 -eJENKINS_AGENT_NAME=agent1 `
 -eTESTCONTAINERS_HOST_OVERRIDE=host.docker.internal `
 --network jenkins `
 --init `
 -it `
 jenkins/inbound-agent
```

```
 docker run --rm `
 -eJENKINS_SECRET=58731f8abec31462273b3db8ccd13a54fb9fb61cb4350fa97aaa0eb42826c551 `
 -eJENKINS_URL=http://host.docker.internal:8082 `
 -eJENKINS_AGENT_NAME=agent1 ` 
 --network jenkins `
 --init `
 -it `
 jenkins/inbound-agent
```

# Tests

```
docker run --init jenkins/inbound-agent -url http://127.0.0.1:8081 97bf15d8d02f8f6a7f932669f9c8defabe1a3cbc9b38b07c00e0c02aebd6d243 agent1
docker run --init jenkins/inbound-agent -url http://localhost:8082 58731f8abec31462273b3db8ccd13a54fb9fb61cb4350fa97aaa0eb42826c551 agent1
```

#Refs:

[Dedicated agents are not able to connect](https://docs.cloudbees.com/docs/cloudbees-ci-kb/latest/client-and-managed-controllers/how-to-troubleshoot-jnlp-agents-connection-issues-with-jenkins)  
[Dedicated agents are not able to connect](https://stackoverflow.com/questions/67196036/how-to-find-jenkins-version-through-cli-command-is-there-any-command-like-java)  

Starting in Jenkins core 2.217 the WebSocket feature landed in Jenkins.
This improvement provides WebSocket transport agent support to Jenkins, available when 
connecting inbound agents or when running the CLI. 
The WebSocket protocol allows bidirectional, streaming communication over an HTTP(S) port.
Since CloudBees Core 2.222.1.1 you can use WebSocket transport to connect inbound agents, 
and this works as well for shared agents / clouds. 
Just select the WebSocket checkbox in agent / cloud configuration. 
No special network configuration is needed, since the regular HTTP(S) port proxied by 
the CloudBees CI ingress is used for all communications.
 
```
jenkins-blueocean
$  java -jar /usr/share/jenkins/jenkins.war --version
2.426.1

jenkins/jenkins:lts
$  java -jar /usr/share/jenkins/jenkins.war --version
2.414.3
```

---

# https://docs.cloudbees.com/docs/cloudbees-ci-kb/latest/client-and-managed-controllers/how-to-troubleshoot-jnlp-agents-connection-issues-with-jenkins
# https://devopscube.com/docker-containers-as-build-slaves-jenkins/


# https://serverfault.com/questions/1054310/how-to-fix-error-while-connecting-to-jenkins-windows-slaves
# https://stackoverflow.com/questions/65655733/failed-to-connect-to-http-localhost8080-tcpslaveagentlistener-connection-re  
# https://github.com/jenkinsci/docker-inbound-agent/issues/146  
# https://serverfault.com/questions/1114513/jenkins-agent-is-not-honoring-hudson-tcpslaveagentlistener-hostname 
# https://community.jenkins.io/t/unable-to-start-inbound-agent-on-windows-server/2251/6

---

### Jenkins Master

[Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/)   
[Install Docker Desktop on Linux](https://docs.docker.com/desktop/install/linux-install/)  
[What is the difference between Docker Desktop for Linux and Docker Engine?](https://docs.docker.com/desktop/faqs/linuxfaqs/#what-is-the-difference-between-docker-desktop-for-linux-and-docker-engine)   

http://52.178.43.220:8080/

```
uname -a && cat /etc/*release

Linux jenkins-get-started-vm 6.2.0-1015-azure #15~22.04.1-Ubuntu SMP Fri Oct  6 13:20:44 UTC 2023 x86_64 x86_64 x86_64 GNU/Linux
DISTRIB_ID=Ubuntu
DISTRIB_RELEASE=22.04
DISTRIB_CODENAME=jammy
DISTRIB_DESCRIPTION="Ubuntu 22.04.3 LTS"
PRETTY_NAME="Ubuntu 22.04.3 LTS"
NAME="Ubuntu"
VERSION_ID="22.04"
VERSION="22.04.3 LTS (Jammy Jellyfish)"
VERSION_CODENAME=jammy
ID=ubuntu
ID_LIKE=debian
HOME_URL="https://www.ubuntu.com/"
SUPPORT_URL="https://help.ubuntu.com/"
BUG_REPORT_URL="https://bugs.launchpad.net/ubuntu/"
PRIVACY_POLICY_URL="https://www.ubuntu.com/legal/terms-and-policies/privacy-policy"
UBUNTU_CODENAME=jammy
```

```
sudo apt update
sudo apt install docker.io -y << DO NOT USE THIS!
```

[Docker Installation Methods on Ubuntu](https://docs.docker.com/engine/install/ubuntu/#installation-methods)
[docker ls](https://docs.docker.com/engine/reference/commandline/container_ls/)
[docker rm](https://docs.docker.com/engine/reference/commandline/rm/)

```
sudo docker run hello-world
sudo docker container ls --all
sudo docker rm --force 86df7e87c987 
sudo service docker status
```

[Configure remote access for Docker daemon](https://docs.docker.com/config/daemon/remote-access/)

```
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H fd:// -H tcp://127.0.0.1:2375

[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock
```

---

[systemctl edit failed (canceled: temporary file is empty) #24208](https://github.com/systemd/systemd/issues/24208)  

```
### Editing /etc/systemd/system/docker.service.d/override.conf
### Anything between here and the comment below will become the new contents of the file

************* YOU MUST PLACE THE EXCERPT ABOVE HERE !!!!! ***************


### Lines below this comment will be discarded

### /lib/systemd/system/docker.service
# [Unit]
# Description=Docker Application Container Engine
# Documentation=https://docs.docker.com
# After=network-online.target docker.socket firewalld.service containerd.service time-set.target
# Wants=network-online.target containerd.service
# Requires=docker.socket
# 
# [Service]
# Type=notify
# # the default is not to use systemd for cgroups because the delegate issues still
# # exists and systemd currently does not support the cgroup feature set required
# # for containers run by docker
# ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
# ExecReload=/bin/kill -s HUP $MAINPID
# TimeoutStartSec=0
```

```
sudo systemctl daemon-reload
sudo systemctl restart docker.service

sudo apt update
sudo apt install net-tools
sudo netstat -lntp | grep dockerd
```

### Verify 1

```
tcp6       0      0 :::4243                 :::*                    LISTEN      5661/dockerd 
```

### Verify 2

```
curl http://localhost:4243/version

{"Platform":{"Name":"Docker Engine - Community"},"Components":[{"Name":"Engine","Version":"24.0.7","Details":{"ApiVersion":"1.43","Arch":"amd64","BuildTime":"2023-10-26T09:07:41.000000000+00:00","Experimental":"false","GitCommit":"311b9ff","GoVersion":"go1.20.10","KernelVersion":"6.2.0-1016-azure","MinAPIVersion":"1.12","Os":"linux"}},{"Name":"containerd","Version":"1.6.25","Details":{"GitCommit":"d8f198a4ed8892c764191ef7b3b06d8a2eeb5c7f"}},{"Name":"runc","Version":"1.1.10","Details":{"GitCommit":"v1.1.10-0-g18a0cb0"}},{"Name":"docker-init","Version":"0.19.0","Details":{"GitCommit":"de40ad0"}}],"Version":"24.0.7","ApiVersion":"1.43","MinAPIVersion":"1.12","GitCommit":"311b9ff","GoVersion":"go1.20.10","Os":"linux","Arch":"amd64","KernelVersion":"6.2.0-1016-azure","BuildTime":"2023-10-26T09:07:41.000000000+00:00"}
```
---

[Linux Ping Command Tutorial with Examples](https://phoenixnap.com/kb/linux-ping-command-examples)

```
sudo ping -c 3 jenkins-docker-host
```

[How to Fix the “java command not found” in Linux?](https://itslinuxfoss.com/how-to-fix-the-java-command-not-found-in-linux/?utm_content=cmp-true)  

---

[How to Setup Jenkins Agent Using SSH [Password & SSH Key]](https://devopscube.com/setup-slaves-on-jenkins-2/)
[Become root user on Linux servers in Azure](https://mohitgoyal.co/2017/08/21/become-root-user-on-linux-servers-in-azure/)

```
ssh admin20231020@172.201.121.242
```


---

[Tutorial: Use Azure Container Instances as a Jenkins build agent](https://learn.microsoft.com/en-us/azure/developer/jenkins/azure-container-instances-as-jenkins-build-agent?ns-enrollment-type=Collection&ns-enrollment-id=5z3diykmy4r0xg)  

[GitHub-Tutorial: Use Azure Container Instances as a Jenkins build agent](https://github.com/MicrosoftDocs/azure-dev-docs/blob/main/articles/jenkins/azure-container-instances-as-jenkins-build-agent.md)

[Test Jenkins Controller Nodes Ports are open On NSG](https://serverfault.com/questions/309357/ping-a-specific-port) 

You need to choose a port over which the inbound Jenkins container node can connect to its Jenkins Controller.
You must make sure that this port is open over TCP through the NSG.

```
Test-NetConnection 52.178.43.220 -port 8080

ComputerName     : 52.178.43.220
RemoteAddress    : 52.178.43.220
RemotePort       : 8080
InterfaceAlias   : Ethernet 3
SourceAddress    : 10.62.4.183
TcpTestSucceeded : True

Test-NetConnection 52.178.43.220 -port 5000

ComputerName     : 52.178.43.220
RemoteAddress    : 52.178.43.220
RemotePort       : 5000
InterfaceAlias   : Ethernet 3
SourceAddress    : 10.62.4.183
TcpTestSucceeded : True
```

[Create Azure Container Instance with CLI](https://learn.microsoft.com/en-us/azure/developer/jenkins/azure-container-instances-as-jenkins-build-agent?ns-enrollment-type=Collection&ns-enrollment-id=5z3diykmy4r0xg#create-azure-container-instance-with-cli)  

The following is the command as it is presented on the tutorial page.

```
az container create \
  --name my-dock \
  --resource-group my-resourcegroup \
  --ip-address Public --image jenkins/inbound-agent:latest \
  --os-type linux \
  --ports 80 \
  --command-line "jenkins-agent -url http://jenkinsserver:port <JENKINS_SECRET> <AGENT_NAME>"
```

However, the command must be modified by merging it with the instuctions presented at
[GitHub - docker-inbound-agent - Docker image for inbound Jenkins agents](https://github.com/jenkinsci/docker-inbound-agent)

The following have been replaced as shown

| Placeholder       | Value           | Description |
| ----------------- | --------------- | ----------- |
| jenkinsserver     | 52.178.43.220   | The IP address or DNS name of the server that hotsts the Jenkins Controller |
| port              | 5000            | The TCP port on the Jenkins Controller Host that has been designated to receive the inbound connection from the container |

#### Bash

```
az container create \
  --name inbound1 \
  --resource-group "jenkins-get-started-rg" \
  --ip-address Public --image jenkins/inbound-agent:latest \
  --os-type linux \
  --ports 80 \
  --command-line "jenkins-agent -url http://52.178.43.220:5000 33a18e41c5ebe45427cbc3e6ef947856dcb76d1996a7a8ad0dfd65fce8c3200a docker-inbound-agent-1"
```

#### PowerShell

```
az container create `
  --name inbound1 `
  --resource-group "jenkins-get-started-rg" `
  --ip-address Public --image jenkins/inbound-agent `
  --os-type linux `
  --ports 80 `
  --command-line "jenkins-agent -url http://52.178.43.220:8080 33a18e41c5ebe45427cbc3e6ef947856dcb76d1996a7a8ad0dfd65fce8c3200a docker-inbound-agent-1"
```

---

### Azure Container Agents Jenkins Plugin

This is another way to connect a Jenkins controller to soem kind of Cloud provider such a 
Azure Container Instances or Azure Kubernettes Services

[Azure Container Agents](https://plugins.jenkins.io/azure-container-agents/)   

[Create an Azure service principal with Azure CLI](https://learn.microsoft.com/en-us/cli/azure/azure-cli-sp-tutorial-1?toc=%2Fazure%2Fazure-resource-manager%2Ftoc.json&tabs=bash)  

[Create a Microsoft Entra application and service principal that can access resources](https://learn.microsoft.com/en-us/entra/identity-platform/howto-create-service-principal-portal)
---




