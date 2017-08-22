// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/config.proto

package org.tensorflow.framework;

/**
 * Protobuf type {@code tensorflow.OptimizerOptions}
 *
 * <pre>
 * Options passed to the graph optimizer
 * </pre>
 */
public  final class OptimizerOptions extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:tensorflow.OptimizerOptions)
    OptimizerOptionsOrBuilder {
  // Use OptimizerOptions.newBuilder() to construct.
  private OptimizerOptions(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private OptimizerOptions() {
    doCommonSubexpressionElimination_ = false;
    doConstantFolding_ = false;
    doFunctionInlining_ = false;
    optLevel_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OptimizerOptions(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            doCommonSubexpressionElimination_ = input.readBool();
            break;
          }
          case 16: {

            doConstantFolding_ = input.readBool();
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            optLevel_ = rawValue;
            break;
          }
          case 32: {

            doFunctionInlining_ = input.readBool();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.framework.ConfigProtos.internal_static_tensorflow_OptimizerOptions_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.framework.ConfigProtos.internal_static_tensorflow_OptimizerOptions_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.framework.OptimizerOptions.class, org.tensorflow.framework.OptimizerOptions.Builder.class);
  }

  /**
   * Protobuf enum {@code tensorflow.OptimizerOptions.Level}
   *
   * <pre>
   * Optimization level
   * </pre>
   */
  public enum Level
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>L1 = 0;</code>
     *
     * <pre>
     * L1 is the default level.
     * Optimization performed at L1 :
     * 1. Common subexpression elimination
     * </pre>
     */
    L1(0, 0),
    /**
     * <code>L2 = 2;</code>
     *
     * <pre>
     * Optimization performed at L2 :
     * 1. Common subexpression elimination
     * 2. Constant folding
     * </pre>
     */
    L2(1, 2),
    /**
     * <code>L0 = -1;</code>
     *
     * <pre>
     * No optimizations
     * </pre>
     */
    L0(2, -1),
    UNRECOGNIZED(-1, -1),
    ;

    /**
     * <code>L1 = 0;</code>
     *
     * <pre>
     * L1 is the default level.
     * Optimization performed at L1 :
     * 1. Common subexpression elimination
     * </pre>
     */
    public static final int L1_VALUE = 0;
    /**
     * <code>L2 = 2;</code>
     *
     * <pre>
     * Optimization performed at L2 :
     * 1. Common subexpression elimination
     * 2. Constant folding
     * </pre>
     */
    public static final int L2_VALUE = 2;
    /**
     * <code>L0 = -1;</code>
     *
     * <pre>
     * No optimizations
     * </pre>
     */
    public static final int L0_VALUE = -1;


    public final int getNumber() {
      if (index == -1) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    public static Level valueOf(int value) {
      switch (value) {
        case 0: return L1;
        case 2: return L2;
        case -1: return L0;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Level>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Level> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Level>() {
            public Level findValueByNumber(int number) {
              return Level.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.tensorflow.framework.OptimizerOptions.getDescriptor().getEnumTypes().get(0);
    }

    private static final Level[] VALUES = values();

    public static Level valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private Level(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:tensorflow.OptimizerOptions.Level)
  }

  public static final int DO_COMMON_SUBEXPRESSION_ELIMINATION_FIELD_NUMBER = 1;
  private boolean doCommonSubexpressionElimination_;
  /**
   * <code>optional bool do_common_subexpression_elimination = 1;</code>
   *
   * <pre>
   * If true, optimize the graph using common subexpression elimination.
   * </pre>
   */
  public boolean getDoCommonSubexpressionElimination() {
    return doCommonSubexpressionElimination_;
  }

  public static final int DO_CONSTANT_FOLDING_FIELD_NUMBER = 2;
  private boolean doConstantFolding_;
  /**
   * <code>optional bool do_constant_folding = 2;</code>
   *
   * <pre>
   * If true, perform constant folding optimization on the graph.
   * </pre>
   */
  public boolean getDoConstantFolding() {
    return doConstantFolding_;
  }

  public static final int DO_FUNCTION_INLINING_FIELD_NUMBER = 4;
  private boolean doFunctionInlining_;
  /**
   * <code>optional bool do_function_inlining = 4;</code>
   *
   * <pre>
   * If true, perform function inlining on the graph.
   * </pre>
   */
  public boolean getDoFunctionInlining() {
    return doFunctionInlining_;
  }

  public static final int OPT_LEVEL_FIELD_NUMBER = 3;
  private int optLevel_;
  /**
   * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
   */
  public int getOptLevelValue() {
    return optLevel_;
  }
  /**
   * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
   */
  public org.tensorflow.framework.OptimizerOptions.Level getOptLevel() {
    org.tensorflow.framework.OptimizerOptions.Level result = org.tensorflow.framework.OptimizerOptions.Level.valueOf(optLevel_);
    return result == null ? org.tensorflow.framework.OptimizerOptions.Level.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (doCommonSubexpressionElimination_ != false) {
      output.writeBool(1, doCommonSubexpressionElimination_);
    }
    if (doConstantFolding_ != false) {
      output.writeBool(2, doConstantFolding_);
    }
    if (optLevel_ != org.tensorflow.framework.OptimizerOptions.Level.L1.getNumber()) {
      output.writeEnum(3, optLevel_);
    }
    if (doFunctionInlining_ != false) {
      output.writeBool(4, doFunctionInlining_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (doCommonSubexpressionElimination_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, doCommonSubexpressionElimination_);
    }
    if (doConstantFolding_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, doConstantFolding_);
    }
    if (optLevel_ != org.tensorflow.framework.OptimizerOptions.Level.L1.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, optLevel_);
    }
    if (doFunctionInlining_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(4, doFunctionInlining_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static org.tensorflow.framework.OptimizerOptions parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static org.tensorflow.framework.OptimizerOptions parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static org.tensorflow.framework.OptimizerOptions parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static org.tensorflow.framework.OptimizerOptions parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.tensorflow.framework.OptimizerOptions prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code tensorflow.OptimizerOptions}
   *
   * <pre>
   * Options passed to the graph optimizer
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.OptimizerOptions)
      org.tensorflow.framework.OptimizerOptionsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.framework.ConfigProtos.internal_static_tensorflow_OptimizerOptions_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.framework.ConfigProtos.internal_static_tensorflow_OptimizerOptions_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.framework.OptimizerOptions.class, org.tensorflow.framework.OptimizerOptions.Builder.class);
    }

    // Construct using org.tensorflow.framework.OptimizerOptions.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      doCommonSubexpressionElimination_ = false;

      doConstantFolding_ = false;

      doFunctionInlining_ = false;

      optLevel_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.framework.ConfigProtos.internal_static_tensorflow_OptimizerOptions_descriptor;
    }

    public org.tensorflow.framework.OptimizerOptions getDefaultInstanceForType() {
      return org.tensorflow.framework.OptimizerOptions.getDefaultInstance();
    }

    public org.tensorflow.framework.OptimizerOptions build() {
      org.tensorflow.framework.OptimizerOptions result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.tensorflow.framework.OptimizerOptions buildPartial() {
      org.tensorflow.framework.OptimizerOptions result = new org.tensorflow.framework.OptimizerOptions(this);
      result.doCommonSubexpressionElimination_ = doCommonSubexpressionElimination_;
      result.doConstantFolding_ = doConstantFolding_;
      result.doFunctionInlining_ = doFunctionInlining_;
      result.optLevel_ = optLevel_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.tensorflow.framework.OptimizerOptions) {
        return mergeFrom((org.tensorflow.framework.OptimizerOptions)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.framework.OptimizerOptions other) {
      if (other == org.tensorflow.framework.OptimizerOptions.getDefaultInstance()) return this;
      if (other.getDoCommonSubexpressionElimination() != false) {
        setDoCommonSubexpressionElimination(other.getDoCommonSubexpressionElimination());
      }
      if (other.getDoConstantFolding() != false) {
        setDoConstantFolding(other.getDoConstantFolding());
      }
      if (other.getDoFunctionInlining() != false) {
        setDoFunctionInlining(other.getDoFunctionInlining());
      }
      if (other.optLevel_ != 0) {
        setOptLevelValue(other.getOptLevelValue());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.tensorflow.framework.OptimizerOptions parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.tensorflow.framework.OptimizerOptions) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean doCommonSubexpressionElimination_ ;
    /**
     * <code>optional bool do_common_subexpression_elimination = 1;</code>
     *
     * <pre>
     * If true, optimize the graph using common subexpression elimination.
     * </pre>
     */
    public boolean getDoCommonSubexpressionElimination() {
      return doCommonSubexpressionElimination_;
    }
    /**
     * <code>optional bool do_common_subexpression_elimination = 1;</code>
     *
     * <pre>
     * If true, optimize the graph using common subexpression elimination.
     * </pre>
     */
    public Builder setDoCommonSubexpressionElimination(boolean value) {
      
      doCommonSubexpressionElimination_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool do_common_subexpression_elimination = 1;</code>
     *
     * <pre>
     * If true, optimize the graph using common subexpression elimination.
     * </pre>
     */
    public Builder clearDoCommonSubexpressionElimination() {
      
      doCommonSubexpressionElimination_ = false;
      onChanged();
      return this;
    }

    private boolean doConstantFolding_ ;
    /**
     * <code>optional bool do_constant_folding = 2;</code>
     *
     * <pre>
     * If true, perform constant folding optimization on the graph.
     * </pre>
     */
    public boolean getDoConstantFolding() {
      return doConstantFolding_;
    }
    /**
     * <code>optional bool do_constant_folding = 2;</code>
     *
     * <pre>
     * If true, perform constant folding optimization on the graph.
     * </pre>
     */
    public Builder setDoConstantFolding(boolean value) {
      
      doConstantFolding_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool do_constant_folding = 2;</code>
     *
     * <pre>
     * If true, perform constant folding optimization on the graph.
     * </pre>
     */
    public Builder clearDoConstantFolding() {
      
      doConstantFolding_ = false;
      onChanged();
      return this;
    }

    private boolean doFunctionInlining_ ;
    /**
     * <code>optional bool do_function_inlining = 4;</code>
     *
     * <pre>
     * If true, perform function inlining on the graph.
     * </pre>
     */
    public boolean getDoFunctionInlining() {
      return doFunctionInlining_;
    }
    /**
     * <code>optional bool do_function_inlining = 4;</code>
     *
     * <pre>
     * If true, perform function inlining on the graph.
     * </pre>
     */
    public Builder setDoFunctionInlining(boolean value) {
      
      doFunctionInlining_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool do_function_inlining = 4;</code>
     *
     * <pre>
     * If true, perform function inlining on the graph.
     * </pre>
     */
    public Builder clearDoFunctionInlining() {
      
      doFunctionInlining_ = false;
      onChanged();
      return this;
    }

    private int optLevel_ = 0;
    /**
     * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
     */
    public int getOptLevelValue() {
      return optLevel_;
    }
    /**
     * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
     */
    public Builder setOptLevelValue(int value) {
      optLevel_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
     */
    public org.tensorflow.framework.OptimizerOptions.Level getOptLevel() {
      org.tensorflow.framework.OptimizerOptions.Level result = org.tensorflow.framework.OptimizerOptions.Level.valueOf(optLevel_);
      return result == null ? org.tensorflow.framework.OptimizerOptions.Level.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
     */
    public Builder setOptLevel(org.tensorflow.framework.OptimizerOptions.Level value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      optLevel_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .tensorflow.OptimizerOptions.Level opt_level = 3;</code>
     */
    public Builder clearOptLevel() {
      
      optLevel_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:tensorflow.OptimizerOptions)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.OptimizerOptions)
  private static final org.tensorflow.framework.OptimizerOptions DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.framework.OptimizerOptions();
  }

  public static org.tensorflow.framework.OptimizerOptions getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OptimizerOptions>
      PARSER = new com.google.protobuf.AbstractParser<OptimizerOptions>() {
    public OptimizerOptions parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new OptimizerOptions(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<OptimizerOptions> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OptimizerOptions> getParserForType() {
    return PARSER;
  }

  public org.tensorflow.framework.OptimizerOptions getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
