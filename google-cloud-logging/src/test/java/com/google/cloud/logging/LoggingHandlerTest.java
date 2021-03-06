/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.Logging.WriteOption;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.ErrorManager;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingHandlerTest {

  private static final String LOG_NAME = "java.log";
  private static final String MESSAGE = "message";
  private static final String PROJECT = "project";
  private static final MonitoredResource DEFAULT_RESOURCE =
      MonitoredResource.of("global", ImmutableMap.of("project_id", PROJECT));
  private static final LogEntry FINEST_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.DEBUG)
      .addLabel("levelName", "FINEST")
      .addLabel("levelValue", String.valueOf(Level.FINEST.intValue()))
      .build();
  private static final LogEntry FINER_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.DEBUG)
      .addLabel("levelName", "FINER")
      .addLabel("levelValue", String.valueOf(Level.FINER.intValue()))
      .build();
  private static final LogEntry FINE_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.DEBUG)
      .addLabel("levelName", "FINE")
      .addLabel("levelValue", String.valueOf(Level.FINE.intValue()))
      .build();
  private static final LogEntry CONFIG_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.INFO)
      .addLabel("levelName", "CONFIG")
      .addLabel("levelValue", String.valueOf(Level.CONFIG.intValue()))
      .build();
  private static final LogEntry INFO_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.INFO)
      .addLabel("levelName", "INFO")
      .addLabel("levelValue", String.valueOf(Level.INFO.intValue()))
      .build();
  private static final LogEntry WARNING_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.WARNING)
      .addLabel("levelName", "WARNING")
      .addLabel("levelValue", String.valueOf(Level.WARNING.intValue()))
      .build();
  private static final LogEntry SEVERE_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.ERROR)
      .addLabel("levelName", "SEVERE")
      .addLabel("levelValue", String.valueOf(Level.SEVERE.intValue()))
      .build();
  private static final LogEntry DEBUG_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.DEBUG)
      .addLabel("levelName", "DEBUG")
      .addLabel("levelValue", String.valueOf(LoggingLevel.DEBUG.intValue()))
      .build();
  private static final LogEntry NOTICE_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.NOTICE)
      .addLabel("levelName", "NOTICE")
      .addLabel("levelValue", String.valueOf(LoggingLevel.NOTICE.intValue()))
      .build();
  private static final LogEntry ERROR_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.ERROR)
      .addLabel("levelName", "ERROR")
      .addLabel("levelValue", String.valueOf(LoggingLevel.ERROR.intValue()))
      .build();
  private static final LogEntry CRITICAL_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.CRITICAL)
      .addLabel("levelName", "CRITICAL")
      .addLabel("levelValue", String.valueOf(LoggingLevel.CRITICAL.intValue()))
      .build();
  private static final LogEntry ALERT_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.ALERT)
      .addLabel("levelName", "ALERT")
      .addLabel("levelValue", String.valueOf(LoggingLevel.ALERT.intValue()))
      .build();
  private static final LogEntry EMERGENCY_ENTRY = LogEntry.newBuilder(StringPayload.of(MESSAGE))
      .setSeverity(Severity.EMERGENCY)
      .addLabel("levelName", "EMERGENCY")
      .addLabel("levelValue", String.valueOf(LoggingLevel.EMERGENCY.intValue()))
      .build();

  private Logging logging;
  private LoggingOptions options;

  static final class TestFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
      return record.getMessage();
    }
  }

  @Before
  public void setUp() {
    logging = EasyMock.createStrictMock(Logging.class);
    options = EasyMock.createStrictMock(LoggingOptions.class);
  }

  @After
  public void afterClass() {
    EasyMock.verify(logging, options);
  }

  @Test
  public void testPublishLevels() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    logging.write(ImmutableList.of(FINEST_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(FINER_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(FINE_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(CONFIG_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(INFO_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(WARNING_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(SEVERE_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(DEBUG_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(NOTICE_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(ERROR_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(CRITICAL_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(ALERT_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.write(ImmutableList.of(EMERGENCY_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    EasyMock.replay(options, logging);
    Handler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setFormatter(new TestFormatter());
    // default levels
    handler.publish(new LogRecord(Level.FINEST, MESSAGE));
    handler.publish(new LogRecord(Level.FINER, MESSAGE));
    handler.publish(new LogRecord(Level.FINE, MESSAGE));
    handler.publish(new LogRecord(Level.CONFIG, MESSAGE));
    handler.publish(new LogRecord(Level.INFO, MESSAGE));
    handler.publish(new LogRecord(Level.WARNING, MESSAGE));
    handler.publish(new LogRecord(Level.SEVERE, MESSAGE));
    // Logging levels
    handler.publish(new LogRecord(LoggingLevel.DEBUG, MESSAGE));
    handler.publish(new LogRecord(LoggingLevel.NOTICE, MESSAGE));
    handler.publish(new LogRecord(LoggingLevel.ERROR, MESSAGE));
    handler.publish(new LogRecord(LoggingLevel.CRITICAL, MESSAGE));
    handler.publish(new LogRecord(LoggingLevel.ALERT, MESSAGE));
    handler.publish(new LogRecord(LoggingLevel.EMERGENCY, MESSAGE));
  }

  @Test
  public void testPublishCustomResource() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    MonitoredResource resource = MonitoredResource.of("custom", ImmutableMap.<String, String>of());
    logging.write(ImmutableList.of(FINEST_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(resource));
    EasyMock.expectLastCall();
    EasyMock.replay(options, logging);
    Handler handler = new LoggingHandler(LOG_NAME, options, resource);
    handler.setLevel(Level.ALL);
    handler.setFormatter(new TestFormatter());
    handler.publish(new LogRecord(Level.FINEST, MESSAGE));
  }

  @Test
  public void testReportFlushError() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    RuntimeException ex = new RuntimeException();
    logging.write(ImmutableList.of(FINEST_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall().andThrow(ex);
    EasyMock.replay(options, logging);
    ErrorManager errorManager = EasyMock.createStrictMock(ErrorManager.class);
    errorManager.error(null, ex, ErrorManager.FLUSH_FAILURE);
    EasyMock.expectLastCall();
    EasyMock.replay(errorManager);
    Handler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setErrorManager(errorManager);
    handler.setFormatter(new TestFormatter());
    handler.publish(new LogRecord(Level.FINEST, MESSAGE));
    EasyMock.verify(errorManager);
  }

  @Test
  public void testReportFormatError() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.replay(options, logging);
    Formatter formatter = EasyMock.createStrictMock(Formatter.class);
    RuntimeException ex = new RuntimeException();
    ErrorManager errorManager = EasyMock.createStrictMock(ErrorManager.class);
    errorManager.error(null, ex, ErrorManager.FORMAT_FAILURE);
    EasyMock.expectLastCall();
    LogRecord record = new LogRecord(Level.FINEST, MESSAGE);
    EasyMock.expect(formatter.format(record)).andThrow(ex);
    EasyMock.replay(errorManager, formatter);
    Handler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setErrorManager(errorManager);
    handler.setFormatter(formatter);
    handler.publish(record);
    EasyMock.verify(errorManager, formatter);
  }

  @Test
  public void testFlushSize() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    logging.write(ImmutableList.of(FINEST_ENTRY, FINER_ENTRY, FINE_ENTRY, CONFIG_ENTRY, INFO_ENTRY,
        WARNING_ENTRY), WriteOption.logName(LOG_NAME), WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    EasyMock.replay(options, logging);
    LoggingHandler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setFlushSize(6);
    handler.setFormatter(new TestFormatter());
    handler.publish(new LogRecord(Level.FINEST, MESSAGE));
    handler.publish(new LogRecord(Level.FINER, MESSAGE));
    handler.publish(new LogRecord(Level.FINE, MESSAGE));
    handler.publish(new LogRecord(Level.CONFIG, MESSAGE));
    handler.publish(new LogRecord(Level.INFO, MESSAGE));
    handler.publish(new LogRecord(Level.WARNING, MESSAGE));
  }

  @Test
  public void testFlushLevel() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    logging.write(ImmutableList.of(FINEST_ENTRY, FINER_ENTRY, FINE_ENTRY, CONFIG_ENTRY, INFO_ENTRY,
        WARNING_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    EasyMock.replay(options, logging);
    LoggingHandler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setFlushSize(100);
    handler.setFlushLevel(Level.WARNING);
    handler.setFormatter(new TestFormatter());
    handler.publish(new LogRecord(Level.FINEST, MESSAGE));
    handler.publish(new LogRecord(Level.FINER, MESSAGE));
    handler.publish(new LogRecord(Level.FINE, MESSAGE));
    handler.publish(new LogRecord(Level.CONFIG, MESSAGE));
    handler.publish(new LogRecord(Level.INFO, MESSAGE));
    handler.publish(new LogRecord(Level.WARNING, MESSAGE));
  }

  @Test
  public void testAddHandler() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    logging.write(ImmutableList.of(FINEST_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    EasyMock.replay(options, logging);
    LoggingHandler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setFormatter(new TestFormatter());
    Logger logger = Logger.getLogger(getClass().getName());
    logger.setLevel(Level.ALL);
    LoggingHandler.addHandler(logger, handler);
    logger.finest(MESSAGE);
  }

  @Test
  public void testMaskLoggers() {
    EasyMock.expect(options.projectId()).andReturn(PROJECT);
    EasyMock.replay(options, logging);
    LoggingHandler handler = new LoggingHandler(LOG_NAME, options);
    Logger logger = Logger.getLogger("com.google");
    Logger maskedLogger = Logger.getLogger("com.google.api.client.http");
    maskedLogger.addHandler(handler);
    assertTrue(maskedLogger.getUseParentHandlers());
    assertSame(logger, maskedLogger.getParent());
    LoggingHandler.addHandler(logger, handler);
    assertFalse(maskedLogger.getUseParentHandlers());
    assertEquals(0, maskedLogger.getHandlers().length);
    logger.removeHandler(handler);
  }

  @Test
  public void testClose() throws Exception {
    EasyMock.expect(options.projectId()).andReturn(PROJECT).anyTimes();
    EasyMock.expect(options.service()).andReturn(logging);
    logging.write(ImmutableList.of(FINEST_ENTRY), WriteOption.logName(LOG_NAME),
        WriteOption.resource(DEFAULT_RESOURCE));
    EasyMock.expectLastCall();
    logging.close();
    EasyMock.expectLastCall();
    EasyMock.replay(options, logging);
    Handler handler = new LoggingHandler(LOG_NAME, options);
    handler.setLevel(Level.ALL);
    handler.setFormatter(new TestFormatter());
    handler.publish(new LogRecord(Level.FINEST, MESSAGE));
    handler.close();
    handler.close();
  }
}
