docker run --hostname=6aff00a81f16 --user=jenkins --env=PATH=/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=LANG=C.UTF-8 --env=JENKINS_HOME=/var/jenkins_home --env=JENKINS_SLAVE_AGENT_PORT=50000 --env=REF=/usr/share/jenkins/ref --env=JENKINS_VERSION=2.458 --env=JENKINS_UC=https://updates.jenkins.io --env=JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental --env=JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals --env=COPY_REFERENCE_FILE_LOG=/var/jenkins_home/copy_reference_file.log --env=JAVA_HOME=/opt/java/openjdk --volume=/var/jenkins_home --network=install_my-network -p 127.0.0.1:8081:8080 --restart=unless-stopped --label='com.docker.compose.config-hash=5d11a8ee1c7be42ccec2177239667ede5828cdbab565ab872ac867b598635bbf' --label='com.docker.compose.container-number=1' --label='com.docker.compose.depends_on=' --label='com.docker.compose.image=sha256:3aaba8413e044b8802fbbeb14672092713157093086b140391ff362700d8cf5c' --label='com.docker.compose.oneoff=False' --label='com.docker.compose.project=install' --label='com.docker.compose.project.config_files=C:\Jenkins\course3-jenkins-gs\install\compose.yaml' --label='com.docker.compose.project.working_dir=C:\Jenkins\course3-jenkins-gs\install' --label='com.docker.compose.replace=99815b135eb099230c73fe2161c811bcac3cb83c083861d9eb4e407dad8ce43f' --label='com.docker.compose.service=jenkins' --label='com.docker.compose.version=2.27.0' --label='org.opencontainers.image.description=The Jenkins Continuous Integration and Delivery server' --label='org.opencontainers.image.licenses=MIT' --label='org.opencontainers.image.revision=fa72e0a928b4ce1d4a60194aee0a1d0ace5c36eb' --label='org.opencontainers.image.source=https://github.com/jenkinsci/docker' --label='org.opencontainers.image.title=Official Jenkins Docker image' --label='org.opencontainers.image.url=https://www.jenkins.io/' --label='org.opencontainers.image.vendor=Jenkins project' --label='org.opencontainers.image.version=2.458' --runtime=runc -d jenkins/jenkins

docker run 
--hostname=6aff00a81f16 
--user=jenkins 
--env=PATH=/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin 
--env=LANG=C.UTF-8 
--env=JENKINS_HOME=/var/jenkins_home 
--env=JENKINS_SLAVE_AGENT_PORT=50000 
--env=REF=/usr/share/jenkins/ref 
--env=JENKINS_VERSION=2.458 
--env=JENKINS_UC=https://updates.jenkins.io 
--env=JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental 
--env=JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals 
--env=COPY_REFERENCE_FILE_LOG=/var/jenkins_home/copy_reference_file.log 
--env=JAVA_HOME=/opt/java/openjdk 
--volume=/var/jenkins_home 
--network=install_my-network 
-p 127.0.0.1:8081:8080 
--restart=unless-stopped 
--label='com.docker.compose.config-hash=5d11a8ee1c7be42ccec2177239667ede5828cdbab565ab872ac867b598635bbf' 
--label='com.docker.compose.container-number=1' 
--label='com.docker.compose.depends_on=' 
--label='com.docker.compose.image=sha256:3aaba8413e044b8802fbbeb14672092713157093086b140391ff362700d8cf5c' 
--label='com.docker.compose.oneoff=False' 
--label='com.docker.compose.project=install' 
--label='com.docker.compose.project.config_files=C:\Jenkins\course3-jenkins-gs\install\compose.yaml' 
--label='com.docker.compose.project.working_dir=C:\Jenkins\course3-jenkins-gs\install' 
--label='com.docker.compose.replace=99815b135eb099230c73fe2161c811bcac3cb83c083861d9eb4e407dad8ce43f' 
--label='com.docker.compose.service=jenkins' --label='com.docker.compose.version=2.27.0' 
--label='org.opencontainers.image.description=The Jenkins Continuous Integration and Delivery server' 
--label='org.opencontainers.image.licenses=MIT' 
--label='org.opencontainers.image.revision=fa72e0a928b4ce1d4a60194aee0a1d0ace5c36eb' 
--label='org.opencontainers.image.source=https://github.com/jenkinsci/docker' 
--label='org.opencontainers.image.title=Official Jenkins Docker image' 
--label='org.opencontainers.image.url=https://www.jenkins.io/' 
--label='org.opencontainers.image.vendor=Jenkins project' 
--label='org.opencontainers.image.version=2.458' 
--runtime=runc -d jenkins/jenkins



---
docker network create jenkins

docker run --name jenkins-docker --rm --detach ^
--privileged --network jenkins --network-alias docker ^
--env DOCKER_TLS_CERTDIR=/certs ^
--volume jenkins-docker-certs:/certs/client ^
--volume jenkins-data:/var/jenkins_home ^
--publish 2376:2376 ^
docker:dind

