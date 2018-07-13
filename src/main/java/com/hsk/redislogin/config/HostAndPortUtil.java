package com.hsk.redislogin.config;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://github.com/xetorthio/jedis/blob/master/src/test/java/redis/clients/jedis/tests/HostAndPortUtil.java">참고 소스</a>
 */
public final class HostAndPortUtil {
    private static final String host = "172.20.0.108";    // 호스트 주소
    private static final int    port = 9100;              // 포트

    private static List<HostAndPort> redisHostAndPortList = new ArrayList<HostAndPort>();

    private HostAndPortUtil() {
        throw new InstantiationError("must not instantiate this class"); /** {@link InstantiationError} : abstract class 또는 interface를 new 키워드로 인스턴스화할 때 발생하는 에러. */
    }

    static {
        /*
         * Protocol.DEFAULT_HOST : default host(127.0.0.1)
         * Protocol.DEFAULT_PORT : default port(6379)
         */
        redisHostAndPortList.add(new HostAndPort(host, port));

        String envRedisHosts = System.getProperty("redis-hosts");

        redisHostAndPortList = parseHosts(envRedisHosts, redisHostAndPortList);
    }

    public static List<HostAndPort> parseHosts(String envHosts,
                                               List<HostAndPort> existingHostsAndPorts) {
        if ((envHosts != null) && (envHosts.length() > 0)) {
            String[] hostDefs = envHosts.split(",");

            if ((hostDefs != null) && (hostDefs.length >= 2)) {
                List<HostAndPort> envHostsAndPorts = new ArrayList<HostAndPort>(hostDefs.length);

                for (String hostDef : hostDefs) {
                    String[] hostAndPortParts = HostAndPort.extractParts(hostDef);    // ( host + ":" + port ) format

                    if ((hostAndPortParts != null) && (hostAndPortParts.length == 2)) {
                        String host = hostAndPortParts[0];
                        int port = Protocol.DEFAULT_PORT;

                        try {
                            port = Integer.parseInt(hostAndPortParts[1]);    // 왜 [1]인가?
                        } catch (final NumberFormatException e) {}

                        envHostsAndPorts.add(new HostAndPort(host, port));
                    }
                }

                return envHostsAndPorts;
            }
        }

        return existingHostsAndPorts;
    }

    public static List<HostAndPort> getRedisServers() {
        return redisHostAndPortList;
    }
}
