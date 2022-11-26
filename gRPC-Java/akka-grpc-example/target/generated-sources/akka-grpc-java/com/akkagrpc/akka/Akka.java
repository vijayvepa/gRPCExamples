// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: akka.proto

package com.akkagrpc.akka;

/**
 * Protobuf type {@code com.vijayvepa.akkagrpc.akka.Akka}
 */
public final class Akka extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.vijayvepa.akkagrpc.akka.Akka)
    AkkaOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Akka.newBuilder() to construct.
  private Akka(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Akka() {
    akkaId_ = "";
    title_ = "";
    genre_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Akka();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Akka(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            akkaId_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            title_ = s;
            break;
          }
          case 29: {

            rating_ = input.readFloat();
            break;
          }
          case 32: {

            releaseYear_ = input.readInt32();
            break;
          }
          case 40: {
            int rawValue = input.readEnum();

            genre_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.akkagrpc.akka.AkkaOuterClass.internal_static_com_vijayvepa_akkagrpc_akka_Akka_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.akkagrpc.akka.AkkaOuterClass.internal_static_com_vijayvepa_akkagrpc_akka_Akka_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.akkagrpc.akka.Akka.class, com.akkagrpc.akka.Akka.Builder.class);
  }

  public static final int AKKAID_FIELD_NUMBER = 1;
  private volatile java.lang.Object akkaId_;
  /**
   * <code>string akkaId = 1;</code>
   * @return The akkaId.
   */
  @java.lang.Override
  public java.lang.String getAkkaId() {
    java.lang.Object ref = akkaId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      akkaId_ = s;
      return s;
    }
  }
  /**
   * <code>string akkaId = 1;</code>
   * @return The bytes for akkaId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAkkaIdBytes() {
    java.lang.Object ref = akkaId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      akkaId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TITLE_FIELD_NUMBER = 2;
  private volatile java.lang.Object title_;
  /**
   * <code>string title = 2;</code>
   * @return The title.
   */
  @java.lang.Override
  public java.lang.String getTitle() {
    java.lang.Object ref = title_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      title_ = s;
      return s;
    }
  }
  /**
   * <code>string title = 2;</code>
   * @return The bytes for title.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTitleBytes() {
    java.lang.Object ref = title_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      title_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RATING_FIELD_NUMBER = 3;
  private float rating_;
  /**
   * <code>float rating = 3;</code>
   * @return The rating.
   */
  @java.lang.Override
  public float getRating() {
    return rating_;
  }

  public static final int RELEASEYEAR_FIELD_NUMBER = 4;
  private int releaseYear_;
  /**
   * <code>int32 releaseYear = 4;</code>
   * @return The releaseYear.
   */
  @java.lang.Override
  public int getReleaseYear() {
    return releaseYear_;
  }

  public static final int GENRE_FIELD_NUMBER = 5;
  private int genre_;
  /**
   * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
   * @return The enum numeric value on the wire for genre.
   */
  @java.lang.Override public int getGenreValue() {
    return genre_;
  }
  /**
   * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
   * @return The genre.
   */
  @java.lang.Override public com.akkagrpc.akka.Genre getGenre() {
    @SuppressWarnings("deprecation")
    com.akkagrpc.akka.Genre result = com.akkagrpc.akka.Genre.valueOf(genre_);
    return result == null ? com.akkagrpc.akka.Genre.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(akkaId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, akkaId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(title_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, title_);
    }
    if (java.lang.Float.floatToRawIntBits(rating_) != 0) {
      output.writeFloat(3, rating_);
    }
    if (releaseYear_ != 0) {
      output.writeInt32(4, releaseYear_);
    }
    if (genre_ != com.akkagrpc.akka.Genre.COMEDY.getNumber()) {
      output.writeEnum(5, genre_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(akkaId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, akkaId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(title_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, title_);
    }
    if (java.lang.Float.floatToRawIntBits(rating_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(3, rating_);
    }
    if (releaseYear_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, releaseYear_);
    }
    if (genre_ != com.akkagrpc.akka.Genre.COMEDY.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(5, genre_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.akkagrpc.akka.Akka)) {
      return super.equals(obj);
    }
    com.akkagrpc.akka.Akka other = (com.akkagrpc.akka.Akka) obj;

    if (!getAkkaId()
        .equals(other.getAkkaId())) return false;
    if (!getTitle()
        .equals(other.getTitle())) return false;
    if (java.lang.Float.floatToIntBits(getRating())
        != java.lang.Float.floatToIntBits(
            other.getRating())) return false;
    if (getReleaseYear()
        != other.getReleaseYear()) return false;
    if (genre_ != other.genre_) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + AKKAID_FIELD_NUMBER;
    hash = (53 * hash) + getAkkaId().hashCode();
    hash = (37 * hash) + TITLE_FIELD_NUMBER;
    hash = (53 * hash) + getTitle().hashCode();
    hash = (37 * hash) + RATING_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getRating());
    hash = (37 * hash) + RELEASEYEAR_FIELD_NUMBER;
    hash = (53 * hash) + getReleaseYear();
    hash = (37 * hash) + GENRE_FIELD_NUMBER;
    hash = (53 * hash) + genre_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.akkagrpc.akka.Akka parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akkagrpc.akka.Akka parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akkagrpc.akka.Akka parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.akkagrpc.akka.Akka parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.akkagrpc.akka.Akka parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.akkagrpc.akka.Akka parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.akkagrpc.akka.Akka prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.vijayvepa.akkagrpc.akka.Akka}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.vijayvepa.akkagrpc.akka.Akka)
      com.akkagrpc.akka.AkkaOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.akkagrpc.akka.AkkaOuterClass.internal_static_com_vijayvepa_akkagrpc_akka_Akka_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.akkagrpc.akka.AkkaOuterClass.internal_static_com_vijayvepa_akkagrpc_akka_Akka_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.akkagrpc.akka.Akka.class, com.akkagrpc.akka.Akka.Builder.class);
    }

    // Construct using com.akkagrpc.akka.Akka.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      akkaId_ = "";

      title_ = "";

      rating_ = 0F;

      releaseYear_ = 0;

      genre_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.akkagrpc.akka.AkkaOuterClass.internal_static_com_vijayvepa_akkagrpc_akka_Akka_descriptor;
    }

    @java.lang.Override
    public com.akkagrpc.akka.Akka getDefaultInstanceForType() {
      return com.akkagrpc.akka.Akka.getDefaultInstance();
    }

    @java.lang.Override
    public com.akkagrpc.akka.Akka build() {
      com.akkagrpc.akka.Akka result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.akkagrpc.akka.Akka buildPartial() {
      com.akkagrpc.akka.Akka result = new com.akkagrpc.akka.Akka(this);
      result.akkaId_ = akkaId_;
      result.title_ = title_;
      result.rating_ = rating_;
      result.releaseYear_ = releaseYear_;
      result.genre_ = genre_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.akkagrpc.akka.Akka) {
        return mergeFrom((com.akkagrpc.akka.Akka)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.akkagrpc.akka.Akka other) {
      if (other == com.akkagrpc.akka.Akka.getDefaultInstance()) return this;
      if (!other.getAkkaId().isEmpty()) {
        akkaId_ = other.akkaId_;
        onChanged();
      }
      if (!other.getTitle().isEmpty()) {
        title_ = other.title_;
        onChanged();
      }
      if (other.getRating() != 0F) {
        setRating(other.getRating());
      }
      if (other.getReleaseYear() != 0) {
        setReleaseYear(other.getReleaseYear());
      }
      if (other.genre_ != 0) {
        setGenreValue(other.getGenreValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.akkagrpc.akka.Akka parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.akkagrpc.akka.Akka) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object akkaId_ = "";
    /**
     * <code>string akkaId = 1;</code>
     * @return The akkaId.
     */
    public java.lang.String getAkkaId() {
      java.lang.Object ref = akkaId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        akkaId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string akkaId = 1;</code>
     * @return The bytes for akkaId.
     */
    public com.google.protobuf.ByteString
        getAkkaIdBytes() {
      java.lang.Object ref = akkaId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        akkaId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string akkaId = 1;</code>
     * @param value The akkaId to set.
     * @return This builder for chaining.
     */
    public Builder setAkkaId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      akkaId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string akkaId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAkkaId() {
      
      akkaId_ = getDefaultInstance().getAkkaId();
      onChanged();
      return this;
    }
    /**
     * <code>string akkaId = 1;</code>
     * @param value The bytes for akkaId to set.
     * @return This builder for chaining.
     */
    public Builder setAkkaIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      akkaId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object title_ = "";
    /**
     * <code>string title = 2;</code>
     * @return The title.
     */
    public java.lang.String getTitle() {
      java.lang.Object ref = title_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        title_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string title = 2;</code>
     * @return The bytes for title.
     */
    public com.google.protobuf.ByteString
        getTitleBytes() {
      java.lang.Object ref = title_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        title_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string title = 2;</code>
     * @param value The title to set.
     * @return This builder for chaining.
     */
    public Builder setTitle(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      title_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string title = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTitle() {
      
      title_ = getDefaultInstance().getTitle();
      onChanged();
      return this;
    }
    /**
     * <code>string title = 2;</code>
     * @param value The bytes for title to set.
     * @return This builder for chaining.
     */
    public Builder setTitleBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      title_ = value;
      onChanged();
      return this;
    }

    private float rating_ ;
    /**
     * <code>float rating = 3;</code>
     * @return The rating.
     */
    @java.lang.Override
    public float getRating() {
      return rating_;
    }
    /**
     * <code>float rating = 3;</code>
     * @param value The rating to set.
     * @return This builder for chaining.
     */
    public Builder setRating(float value) {
      
      rating_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float rating = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearRating() {
      
      rating_ = 0F;
      onChanged();
      return this;
    }

    private int releaseYear_ ;
    /**
     * <code>int32 releaseYear = 4;</code>
     * @return The releaseYear.
     */
    @java.lang.Override
    public int getReleaseYear() {
      return releaseYear_;
    }
    /**
     * <code>int32 releaseYear = 4;</code>
     * @param value The releaseYear to set.
     * @return This builder for chaining.
     */
    public Builder setReleaseYear(int value) {
      
      releaseYear_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 releaseYear = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearReleaseYear() {
      
      releaseYear_ = 0;
      onChanged();
      return this;
    }

    private int genre_ = 0;
    /**
     * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
     * @return The enum numeric value on the wire for genre.
     */
    @java.lang.Override public int getGenreValue() {
      return genre_;
    }
    /**
     * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
     * @param value The enum numeric value on the wire for genre to set.
     * @return This builder for chaining.
     */
    public Builder setGenreValue(int value) {
      
      genre_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
     * @return The genre.
     */
    @java.lang.Override
    public com.akkagrpc.akka.Genre getGenre() {
      @SuppressWarnings("deprecation")
      com.akkagrpc.akka.Genre result = com.akkagrpc.akka.Genre.valueOf(genre_);
      return result == null ? com.akkagrpc.akka.Genre.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
     * @param value The genre to set.
     * @return This builder for chaining.
     */
    public Builder setGenre(com.akkagrpc.akka.Genre value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      genre_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.vijayvepa.akkagrpc.akka.Genre genre = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearGenre() {
      
      genre_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.vijayvepa.akkagrpc.akka.Akka)
  }

  // @@protoc_insertion_point(class_scope:com.vijayvepa.akkagrpc.akka.Akka)
  private static final com.akkagrpc.akka.Akka DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.akkagrpc.akka.Akka();
  }

  public static com.akkagrpc.akka.Akka getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Akka>
      PARSER = new com.google.protobuf.AbstractParser<Akka>() {
    @java.lang.Override
    public Akka parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Akka(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Akka> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Akka> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.akkagrpc.akka.Akka getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

