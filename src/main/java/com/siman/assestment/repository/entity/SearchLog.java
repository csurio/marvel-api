package com.siman.assestment.repository.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="searh_log")
public class SearchLog {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "response_date")
    private LocalDateTime responseDate;

    @Column(nullable = false, name = "app_name")
    private String appName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "remote_host")
    private String remoteHost;

    @Column(name = "remote_port")
    private String remotePort;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "url")
    private String url;

    @Column(name = "uri")
    private String uri;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "status")
    private String status;

    @Column(name = "token")
    private String token;
}
