package cn.nukkit.network.protocol;

import cn.nukkit.utils.SemVersion;

import static cn.nukkit.utils.Utils.dynamic;

/**
 * @author MagicDroidX &amp; iNevet (Nukkit Project)
 */
public interface ProtocolInfo {
    /**
     * Actual Minecraft: PE protocol version
     */
    int CURRENT_PROTOCOL = dynamic(686);

    String MINECRAFT_VERSION_NETWORK = dynamic("1.21.2");

    SemVersion MINECRAFT_SEMVERSION = new SemVersion(1, 21, 2, 0, 0);

    int BLOCK_STATE_VERSION_NO_REVISION = (MINECRAFT_SEMVERSION.major() << 24) | //major
            (MINECRAFT_SEMVERSION.minor() << 16) | //minor
            (MINECRAFT_SEMVERSION.patch() << 8); //patch

    String MINECRAFT_VERSION = 'v' + MINECRAFT_VERSION_NETWORK;

    int LOGIN_PACKET = 0x01;
    int PLAY_STATUS_PACKET = 0x02;
    int SERVER_TO_CLIENT_HANDSHAKE_PACKET = 0x03;
    int CLIENT_TO_SERVER_HANDSHAKE_PACKET = 0x04;
    int DISCONNECT_PACKET = 0x05;
    int RESOURCE_PACKS_INFO_PACKET = 0x06;
    int RESOURCE_PACK_STACK_PACKET = 0x07;
    int RESOURCE_PACK_CLIENT_RESPONSE_PACKET = 0x08;
    int TEXT_PACKET = 0x09;
    int SERVER_POST_MOVE_POSITION = 0x10;
    int SET_TIME_PACKET = 0x0a;
    int START_GAME_PACKET = 0x0b;
    int ADD_PLAYER_PACKET = 0x0c;
    int ADD_ENTITY_PACKET = 0x0d;
    int REMOVE_ENTITY_PACKET = 0x0e;
    int ADD_ITEM_ENTITY_PACKET = 0x0f;
    int TAKE_ITEM_ENTITY_PACKET = 0x11;
    int MOVE_ENTITY_ABSOLUTE_PACKET = 0x12;
    int MOVE_PLAYER_PACKET = 0x13;
    int RIDER_JUMP_PACKET = 0x14;
    int UPDATE_BLOCK_PACKET = 0x15;
    int ADD_PAINTING_PACKET = 0x16;
    int TICK_SYNC_PACKET = 0x17;
    int LEVEL_SOUND_EVENT_PACKET_V1 = 0x18;
    int LEVEL_EVENT_PACKET = 0x19;
    int BLOCK_EVENT_PACKET = 0x1a;
    int ENTITY_EVENT_PACKET = 0x1b;
    int MOB_EFFECT_PACKET = 0x1c;
    int UPDATE_ATTRIBUTES_PACKET = 0x1d;
    int INVENTORY_TRANSACTION_PACKET = 0x1e;
    int MOB_EQUIPMENT_PACKET = 0x1f;
    int MOB_ARMOR_EQUIPMENT_PACKET = 0x20;
    int INTERACT_PACKET = 0x21;
    int BLOCK_PICK_REQUEST_PACKET = 0x22;
    int ENTITY_PICK_REQUEST_PACKET = 0x23;
    int PLAYER_ACTION_PACKET = 0x24;
    int ENTITY_FALL_PACKET = 0x25;
    int HURT_ARMOR_PACKET = 0x26;
    int SET_ENTITY_DATA_PACKET = 0x27;
    int SET_ENTITY_MOTION_PACKET = 0x28;
    int SET_ENTITY_LINK_PACKET = 0x29;
    int SET_HEALTH_PACKET = 0x2a;
    int SET_SPAWN_POSITION_PACKET = 0x2b;
    int ANIMATE_PACKET = 0x2c;
    int RESPAWN_PACKET = 0x2d;
    int CONTAINER_OPEN_PACKET = 0x2e;
    int CONTAINER_CLOSE_PACKET = 0x2f;
    int PLAYER_HOTBAR_PACKET = 0x30;
    int INVENTORY_CONTENT_PACKET = 0x31;
    int INVENTORY_SLOT_PACKET = 0x32;
    int CONTAINER_SET_DATA_PACKET = 0x33;
    int CRAFTING_DATA_PACKET = 0x34;
    int CRAFTING_EVENT_PACKET = 0x35;
    int GUI_DATA_PICK_ITEM_PACKET = 0x36;
    int ADVENTURE_SETTINGS_PACKET = 0x37;
    int BLOCK_ENTITY_DATA_PACKET = 0x38;
    int PLAYER_INPUT_PACKET = 0x39;
    int FULL_CHUNK_DATA_PACKET = 0x3a;
    int SET_COMMANDS_ENABLED_PACKET = 0x3b;
    int SET_DIFFICULTY_PACKET = 0x3c;
    int CHANGE_DIMENSION_PACKET = 0x3d;
    int SET_PLAYER_GAME_TYPE_PACKET = 0x3e;
    int PLAYER_LIST_PACKET = 0x3f;
    int SIMPLE_EVENT_PACKET = 0x40;
    int EVENT_PACKET = 0x41;
    int SPAWN_EXPERIENCE_ORB_PACKET = 0x42;
    int CLIENTBOUND_MAP_ITEM_DATA_PACKET = 0x43;
    int MAP_INFO_REQUEST_PACKET = 0x44;
    int REQUEST_CHUNK_RADIUS_PACKET = 0x45;
    int CHUNK_RADIUS_UPDATED_PACKET = 0x46;
    int ITEM_FRAME_DROP_ITEM_PACKET = 0x47;
    int GAME_RULES_CHANGED_PACKET = 0x48;
    int CAMERA_PACKET = 0x49;
    int BOSS_EVENT_PACKET = 0x4a;
    int SHOW_CREDITS_PACKET = 0x4b;
    int AVAILABLE_COMMANDS_PACKET = 0x4c;
    int COMMAND_REQUEST_PACKET = 0x4d;
    int COMMAND_BLOCK_UPDATE_PACKET = 0x4e;
    int COMMAND_OUTPUT_PACKET = 0x4f;
    int UPDATE_TRADE_PACKET = 0x50;
    int UPDATE_EQUIPMENT_PACKET = 0x51;
    int RESOURCE_PACK_DATA_INFO_PACKET = 0x52;
    int RESOURCE_PACK_CHUNK_DATA_PACKET = 0x53;
    int RESOURCE_PACK_CHUNK_REQUEST_PACKET = 0x54;
    int TRANSFER_PACKET = 0x55;
    int PLAY_SOUND_PACKET = 0x56;
    int STOP_SOUND_PACKET = 0x57;
    int SET_TITLE_PACKET = 0x58;
    int ADD_BEHAVIOR_TREE_PACKET = 0x59;
    int STRUCTURE_BLOCK_UPDATE_PACKET = 0x5a;
    int SHOW_STORE_OFFER_PACKET = 0x5b;
    int PURCHASE_RECEIPT_PACKET = 0x5c;
    int PLAYER_SKIN_PACKET = 0x5d;
    int SUB_CLIENT_LOGIN_PACKET = 0x5e;
    int INITIATE_WEB_SOCKET_CONNECTION_PACKET = 0x5f;
    int SET_LAST_HURT_BY_PACKET = 0x60;
    int BOOK_EDIT_PACKET = 0x61;
    int NPC_REQUEST_PACKET = 0x62;
    int PHOTO_TRANSFER_PACKET = 0x63;
    int MODAL_FORM_REQUEST_PACKET = 0x64;
    int MODAL_FORM_RESPONSE_PACKET = 0x65;
    int SERVER_SETTINGS_REQUEST_PACKET = 0x66;
    int SERVER_SETTINGS_RESPONSE_PACKET = 0x67;
    int SHOW_PROFILE_PACKET = 0x68;
    int SET_DEFAULT_GAME_TYPE_PACKET = 0x69;
    int REMOVE_OBJECTIVE_PACKET = 0x6a;
    int SET_DISPLAY_OBJECTIVE_PACKET = 0x6b;
    int SET_SCORE_PACKET = 0x6c;
    int LAB_TABLE_PACKET = 0x6d;
    int UPDATE_BLOCK_SYNCED_PACKET = 0x6e;
    int MOVE_ENTITY_DELTA_PACKET = 0x6f;
    int SET_SCOREBOARD_IDENTITY_PACKET = 0x70;
    int SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET = 0x71;
    int UPDATE_SOFT_ENUM_PACKET = 0x72;
    int NETWORK_STACK_LATENCY_PACKET = 0x73;
    int SCRIPT_CUSTOM_EVENT_PACKET = 0x75;
    int SPAWN_PARTICLE_EFFECT_PACKET = 0x76;
    int AVAILABLE_ENTITY_IDENTIFIERS_PACKET = 0x77;
    int LEVEL_SOUND_EVENT_PACKET_V2 = 0x78;
    int NETWORK_CHUNK_PUBLISHER_UPDATE_PACKET = 0x79;
    int BIOME_DEFINITION_LIST_PACKET = 0x7a;
    int LEVEL_SOUND_EVENT_PACKET = 0x7b;
    int LEVEL_EVENT_GENERIC_PACKET = 0x7c;
    int LECTERN_UPDATE_PACKET = 0x7d;
    int VIDEO_STREAM_CONNECT_PACKET = 0x7e;
    //int ADD_ENTITY_PACKET = 0x7f;
    //int REMOVE_ENTITY_PACKET = 0x80;
    int CLIENT_CACHE_STATUS_PACKET = 0x81;
    int ON_SCREEN_TEXTURE_ANIMATION_PACKET = 0x82;
    int MAP_CREATE_LOCKED_COPY_PACKET = 0x83;
    int STRUCTURE_TEMPLATE_DATA_EXPORT_REQUEST = 0x84;
    int STRUCTURE_TEMPLATE_DATA_EXPORT_RESPONSE = 0x85;
    int UPDATE_BLOCK_PROPERTIES = 0x86;
    int CLIENT_CACHE_BLOB_STATUS_PACKET = 0x87;
    int CLIENT_CACHE_MISS_RESPONSE_PACKET = 0x88;
    int EDUCATION_SETTINGS_PACKET = 0x89;
    int EMOTE_PACKET = 0x8a;
    int MULTIPLAYER_SETTINGS_PACKET = 0x8b;
    int SETTINGS_COMMAND_PACKET = 140;
    int ANVIL_DAMAGE_PACKET = 0x8d;
    int COMPLETED_USING_ITEM_PACKET = 0x8e;
    int NETWORK_SETTINGS_PACKET = 0x8f;
    int PLAYER_AUTH_INPUT_PACKET = 0x90;

    int CREATIVE_CONTENT_PACKET = 0x91;

    int PLAYER_ENCHANT_OPTIONS_PACKET = 0x92;

    int ITEM_STACK_REQUEST_PACKET = 0x93;

    int ITEM_STACK_RESPONSE_PACKET = 0x94;

    int PLAYER_ARMOR_DAMAGE_PACKET = 0x95;

    int CODE_BUILDER_PACKET = 0x96;

    int UPDATE_PLAYER_GAME_TYPE_PACKET = 0x97;

    int EMOTE_LIST_PACKET = 0x98;

    int POS_TRACKING_SERVER_BROADCAST_PACKET = 0x99;

    int POS_TRACKING_CLIENT_REQUEST_PACKET = 0x9a;

    int DEBUG_INFO_PACKET = 0x9b;

    int PACKET_VIOLATION_WARNING_PACKET = 0x9c;

    int MOTION_PREDICTION_HINTS_PACKET = 0x9d;

    int ANIMATE_ENTITY_PACKET = 0x9e;

    int CAMERA_SHAKE_PACKET = 0x9f;

    int PLAYER_FOG_PACKET = 0xa0;

    int CORRECT_PLAYER_MOVE_PREDICTION_PACKET = 0xa1;

    int ITEM_COMPONENT_PACKET = 0xa2;

    int FILTER_TEXT_PACKET = 0xa3;

    int CLIENTBOUND_DEBUG_RENDERER_PACKET = 0xa4;

    int SYNC_ENTITY_PROPERTY_PACKET = 0xa5;

    int ADD_VOLUME_ENTITY_PACKET = 0xa6;

    int REMOVE_VOLUME_ENTITY_PACKET = 0xa7;

    int SIMULATION_TYPE_PACKET = 0xa8;

    int NPC_DIALOGUE_PACKET = 0xa9;

    int EDU_URI_RESOURCE_PACKET = 0xaa;

    int CREATE_PHOTO_PACKET = 0xab;

    int UPDATE_SUB_CHUNK_BLOCKS_PACKET = 0xac;

    int PHOTO_INFO_REQUEST_PACKET = 0xad;

    int SUB_CHUNK_PACKET = 0xae;

    int SUB_CHUNK_REQUEST_PACKET = 0xaf;

    int PLAYER_START_ITEM_COOL_DOWN_PACKET = 0xb0;

    int SCRIPT_MESSAGE_PACKET = 0xb1;

    int CODE_BUILDER_SOURCE_PACKET = 0xb2;

    int AGENT_ACTION_EVENT_PACKET = 0xb3;

    int CHANGE_MOB_PROPERTY_PACKET = 0xb4;

    int DIMENSION_DATA_PACKET = 0xb5;

    int TICKING_AREAS_LOAD_STATUS_PACKET = 0xb6;

    int LESSON_PROGRESS_PACKET = 0xb7;

    int REQUEST_ABILITY_PACKET = 0xb8;

    int REQUEST_PERMISSIONS_PACKET = 0xb9;
    int TOAST_REQUEST_PACKET = 0xba;

    int UPDATE_ABILITIES_PACKET = 0xbb;

    int UPDATE_ADVENTURE_SETTINGS_PACKET = 0xbc;

    int DEATH_INFO_PACKET = 0xbd;

    int EDITOR_NETWORK_PACKET = 0xbe;

    int FEATURE_REGISTRY_PACKET = 0xbf;

    int SERVER_STATS_PACKET = 0xc0;

    int REQUEST_NETWORK_SETTINGS_PACKET = 0xc1;

    int GAME_TEST_REQUEST_PACKET = 0xc2;

    int GAME_TEST_RESULTS_PACKET = 0xc3;
    int UPDATE_CLIENT_INPUT_LOCKS = 0xc4;

    int CLIENT_CHEAT_ABILITY_PACKET = 0xc5;

    int CAMERA_PRESETS_PACKET = 0xc6;

    int UNLOCKED_RECIPES_PACKET = (int) 0xc7;

    int CAMERA_INSTRUCTION_PACKET = 300;

    int COMPRESSED_BIOME_DEFINITIONS_LIST = 301;

    int TRIM_DATA = 302;

    int OPEN_SIGN = 303;

    int AGENT_ANIMATION = 304;

    int REFRESH_ENTITLEMENTS = 305;

    int TOGGLE_CRAFTER_SLOT_REQUEST = 306;

    int SET_PLAYER_INVENTORY_OPTIONS_PACKET = 307;

    int SET_HUD = 308;
}
