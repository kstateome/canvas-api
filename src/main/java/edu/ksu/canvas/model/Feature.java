package edu.ksu.canvas.model;

import java.io.Serializable;
import java.time.Instant;

public class Feature extends BaseCanvasModel implements Serializable {

    private String name;
    private String displayName;
    private String appliesTo;
    private Instant enableAt;
    private FeatureFlag featureFlag;
    private boolean rootOptIn;
    private boolean beta;
    private boolean autoexpand;
    private boolean development;
    private String releaseNotesUrl;
}
