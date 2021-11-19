FROM websphere-liberty:webProfile7

MAINTAINER dllamber@us.ibm.com

ADD server_config/server.xml  /config/
ADD seqConsumer.war  /config/dropins/

EXPOSE 9080


