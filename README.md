1.yum install jdk

	yum install -y java;
	yum install -y maven;
2.apache-tomcat-7.0.96  解压缩版
	cd /;
	wget http://ftp.wayne.edu/apache/tomcat/tomcat-7/v7.0.96/bin/apache-tomcat-7.0.96.tar.gz;
	tar -xzf apache-tomcat-7.0.96.tar.gz;

3.centos7  install  redis 5
   #https://www.cnblogs.com/autohome7390/p/6433956.html
   #https://www.cnblogs.com/linjiqin/p/7965783.html
   #https://blog.csdn.net/CrazyLai1996/article/details/76472856
 
	yum install -y http://rpms.famillecollet.com/enterprise/remi-release-7.rpm;
	yum --enablerepo=remi install redis -y;
	service redis start;
4---youtudl下载
sudo wget https://yt-dl.org/downloads/latest/youtube-dl -O /usr/local/bin/youtube-dl;
sudo chmod a+rx /usr/local/bin/youtube-dl;


mkdir /root/git_zlq/;
cd /root/git_zlq/;
git clone https://github.com/zlqlpp/sellvideoprojectN.git;

######## ~/.sysconfig
alias go='cd /root/git_zlq/sellvideoprojectN;                                                         \
git pull;                                                                                            \
mvn clean package;                                                                                   \
/root/apache-tomcat-7.0.96/bin/shutdown.sh;                                                          \
rm -rf /root/apache-tomcat-7.0.96/webapps/sel*;                                                      \
rm -rf /root/apache-tomcat-7.0.96/webapps/ROOT;                                                      \
rm -rf /root/apache-tomcat-7.0.96/logs/*;                                                            \
cp /root/git_zlq/sellvideoprojectN/target/sellvideoprojectN.war /root/apache-tomcat-7.0.96/webapps;     \
rm -rf /root/apache-tomcat-7.0.96/work/*;                                                            \
rm -rf /root/apache-tomcat-7.0.96/temp/*;                                                            \
rm -rf /var/lib/tomcat/webapps/*;                                                                    \
/root/apache-tomcat-7.0.96/bin/startup.sh; \
vlog; '

alias gon='cd /root/git_zlq/sellvideoprojectN;                                                         \
git pull;                                                                                            \
mvn clean package;                                                                                   \
 cp  -R /root/git_zlq/sellvideoprojectN/target/sellvideoprojectN /root/apache-tomcat-7.0.96/webapps;'

alias cdapp='cd /root/apache-tomcat-7.0.96/webapps'
alias cdlog='cd /root/apache-tomcat-7.0.96/logs'
alias vlog="tail -f /root/apache-tomcat-7.0.96/logs/catalina.out"
alias cdvideo=" cd ~/youtubedl/;ll"
alias cdgit=


#######.bash_profile
alias welcome='clear;source ~/.bash_profile'
source /root/.sysconfig


 source ~/.bash_profile



mkdir /root/youtubedl;
mkdir /root/youtubedl/video;
mkdir /root/youtubedl/img;


修改tomcat  端口80 
host 修改成 域名
<Context path="/" docBase="/root/apache-tomcat-7.0.96/webapps/sellvideoprojectN"  reloadable="true" />
<Context path="/video" docBase="/root/youtubedl/video"  reloadable="true" />
<Context path="/videoimg" docBase="/root/youtubedl/img"  reloadable="true" />



