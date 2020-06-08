/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.google.android.apps.exposurenotification.storage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Objects;

/**
 * An exposure element for display in the exposures UI.
 *
 * <p>Partners should implement a daily TTL/expiry, for on-device storage of this data, and must
 * ensure compliance with all applicable laws and requirements with respect to encryption, storage,
 * and retention polices for end user data.
 */
@Entity
public class ExposureEntity {

  @PrimaryKey(autoGenerate = true)
  long id;

  /**
   * The dateMillisSinceEpoch provided by the ExposureInformation in the Exposure Notifications
   * API.
   *
   * <p>Represents a date of an exposure in millis since epoch rounded to the day.
   */
  @ColumnInfo(name = "date_millis_since_epoch")
  private long dateMillisSinceEpoch;

  /**
   * The timestamp in millis since epoch for when the exposure notification status update was
   * received.
   */
  @ColumnInfo(name = "received_timestamp_ms")
  private long receivedTimestampMs;

  @ColumnInfo(name = "duration_minutes")
  private int durationMinutes;

  @ColumnInfo(name = "attenuation")
  private int attenuation;

  @ColumnInfo(name = "risk_level")
  private int riskLevel;

  @ColumnInfo(name = "risk_score")
  private int riskScore;

  ExposureEntity(long dateMillisSinceEpoch, long receivedTimestampMs, int durationMinutes, int attenuation, int riskLevel, int riskScore) {
    this.receivedTimestampMs = receivedTimestampMs;
    this.dateMillisSinceEpoch = dateMillisSinceEpoch;
    this.durationMinutes = durationMinutes;
    this.attenuation = attenuation;
    this.riskLevel = riskLevel;
    this.riskScore = riskScore;
  }

  /**
   * Creates an ExposureEntity.
   *
   * @param dateMillisSinceEpoch the date of an exposure in millis since epoch rounded to the day of
   *                             the detected exposure
   * @param receivedTimestampMs  the timestamp in milliseconds since epoch for when the exposure was
   *                             received by the app
   */
  public static ExposureEntity create(long dateMillisSinceEpoch, long receivedTimestampMs, int durationMinutes, int attenuation, int riskLevel, int riskScore) {
    return new ExposureEntity(dateMillisSinceEpoch, receivedTimestampMs, durationMinutes, attenuation, riskLevel, riskScore);
  }

  public long getId() {
    return id;
  }

  void setId(long id) {
    this.id = id;
  }

  public long getReceivedTimestampMs() {
    return receivedTimestampMs;
  }

  void setReceivedTimestampMs(long ms) {
    this.receivedTimestampMs = ms;
  }

  public long getDateMillisSinceEpoch() {
    return dateMillisSinceEpoch;
  }

  public void setDateMillisSinceEpoch(long dateMillisSinceEpoch) {
    this.dateMillisSinceEpoch = dateMillisSinceEpoch;
  }

  public int getDurationMinutes() {
    return durationMinutes;
  }

  public void setDurationMinutes(int durationMinutes) {
    this.durationMinutes = durationMinutes;
  }

  public int getAttenuation() {
    return attenuation;
  }

  public void setAttenuation(int attenuation) {
    this.attenuation = attenuation;
  }

  public int getRiskLevel() {
    return riskLevel;
  }

  public void setRiskLevel(int riskLevel) {
    this.riskLevel = riskLevel;
  }

  public int getRiskScore() {
    return riskScore;
  }

  public void setRiskScore(int riskScore) {
    this.riskScore = riskScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExposureEntity that = (ExposureEntity) o;
    return id == that.id &&
        dateMillisSinceEpoch == that.dateMillisSinceEpoch &&
        receivedTimestampMs == that.receivedTimestampMs &&
        durationMinutes == that.durationMinutes &&
        attenuation == that.attenuation &&
        riskLevel == that.riskLevel &&
        riskScore == that.riskScore;
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, dateMillisSinceEpoch, receivedTimestampMs, durationMinutes, attenuation,
            riskLevel,
            riskScore);
  }

  public String debugStuff() {
    return "Duration: "+durationMinutes+" minutes\nAttenuation:"+attenuation+" dBm\nRisk level: "+riskLevel+"\nRisk score: "+riskScore;
  }
}