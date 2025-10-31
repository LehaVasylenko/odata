package odata.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// -----------------------------
// CUSTOMERS (key = CUSTNAME)
// -----------------------------
@Entity
@Table(name = "CUSTOMERS")
public class Customers {
    @Id
    @Column(name = "CUSTNAME")
    private String custname;

    @Column(name = "AUJF_FIRSTNAME")
    private String aujfFirstname;

    @Column(name = "AUJF_LASTNAME")
    private String aujfLastname;

    @Column(name = "CUSTDES")
    private String custdes;

    @Column(name = "CUSTDESLONG")
    private String custdeslong;

    @Column(name = "ECUSTDES")
    private String ecustdes;

    @Column(name = "STATDES")
    private String statdes;

    @Column(name = "OWNERLOGIN")
    private String ownerlogin;

    @Column(name = "INACTIVEFLAG")
    private String inactiveflag;

    @Column(name = "CREATEDDATE")
    private Date createddate;

    @Column(name = "STATUSDATE")
    private Date statusdate;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "AAAA_CUSTNAME")
    private String aaaaCustname;

    @Column(name = "AAAA_CUSTDES2")
    private String aaaaCustdes2;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "AUJF_ANOTHEREMAIL")
    private String aujfAnotheremail;

    @Column(name = "BUSINESSTYPE")
    private String businesstype;

    @Column(name = "AUJF_CHILDNUM")
    private Long aujfChildnum;

    @Column(name = "KERN_BDATE")
    private Date kernBdate;

    @Column(name = "AAAA_GENDER")
    private String aaaaGender;

    @Column(name = "AAAA_AGEGROUPCODE")
    private String aaaaAgegroupcode;

    @Column(name = "AAAA_COUPLENAME")
    private String aaaaCouplename;

    @Column(name = "AAAA_DUTYCODE")
    private String aaaaDutycode;

    @Column(name = "AAAA_DUTYDES")
    private String aaaaDutydes;

    @Column(name = "AAAA_ENGLISH")
    private String aaaaEnglish;

    @Column(name = "AAAA_REMARKS")
    private String aaaaRemarks;

    @Column(name = "MCUSTNAME")
    private String mcustname;

    @Column(name = "MCUSTDES")
    private String mcustdes;

    @Column(name = "CTYPECODE")
    private String ctypecode;

    @Column(name = "CTYPENAME")
    private String ctypename;

    @Column(name = "PCUSTNAME")
    private String pcustname;

    @Column(name = "CTYPE2CODE")
    private String ctype2Code;

    @Column(name = "PCUSTDES")
    private String pcustdes;

    @Column(name = "CTYPE2NAME")
    private String ctype2Name;

    @Column(name = "CUSTPART")
    private String custpart;

    @Column(name = "NSFLAG")
    private String nsflag;

    @Column(name = "STCODE")
    private String stcode;

    @Column(name = "STDES")
    private String stdes;

    @Column(name = "ZONECODE")
    private String zonecode;

    @Column(name = "ZONEDES")
    private String zonedes;

    @Column(name = "TRACK")
    private String track;

    @Column(name = "CUSTNAMEPATNAME")
    private String custnamepatname;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "ADDRESS3")
    private String address3;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STATEA")
    private String statea;

    @Column(name = "STATECODE")
    private String statecode;

    @Column(name = "STATENAME")
    private String statename;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "COUNTRYNAME")
    private String countryname;

    @Column(name = "WTAXNUM")
    private String wtaxnum;

    @Column(name = "WTAXNUMEXPL")
    private String wtaxnumexpl;

    @Column(name = "VATNUM")
    private String vatnum;

    @Column(name = "AGENTCODE")
    private String agentcode;

    @Column(name = "AGENTNAME")
    private String agentname;

    @Column(name = "AGENTCODE2")
    private String agentcode2;

    @Column(name = "AGENTNAME2")
    private String agentname2;

    @Column(name = "TERRITORYCODE")
    private String territorycode;

    @Column(name = "TERRITORYDES")
    private String territorydes;

    @Column(name = "COMMISSION")
    private Double commission;

    @Column(name = "AUJF_CANDIDATEDES")
    private String aujfCandidatedes;

    @Column(name = "ESTABLISHED")
    private String established;

    @Column(name = "EMPNUM")
    private Long empnum;

    @Column(name = "BRANCHNAME")
    private String branchname;

    @Column(name = "BRANCHDES")
    private String branchdes;

    @Column(name = "PAYCODE")
    private String paycode;

    @Column(name = "PAYDES")
    private String paydes;

    @Column(name = "MAX_CREDIT")
    private Double maxCredit;

    @Column(name = "MAX_OBLIGO")
    private Double maxObligo;

    @Column(name = "OBCODE")
    private String obcode;

    @Column(name = "DISTRLINECODE")
    private String distrlinecode;

    @Column(name = "DISTRLINEDES")
    private String distrlinedes;

    @Column(name = "UNLOADTIME")
    private String unloadtime;

    @Column(name = "DISTRORDER")
    private Long distrorder;

    @Column(name = "NOTALLOWFORECAST")
    private String notallowforecast;

    @Column(name = "BONUSFLAG")
    private String bonusflag;

    @Column(name = "COMPETITORFLAG")
    private String competitorflag;

    @Column(name = "FORECAST")
    private String forecast;

    @Column(name = "CHANEL")
    private String chanel;

    @Column(name = "DISTRTYPECODE")
    private String distrtypecode;

    @Column(name = "DISTRTYPEDES")
    private String distrtypedes;

    @Column(name = "SECONDLANGTEXT")
    private String secondlangtext;

    @Column(name = "CONFIDENTIAL")
    private String confidential;

    @Column(name = "HOSTNAME")
    private String hostname;

    @Column(name = "SPEC1")
    private String spec1;

    @Column(name = "SPEC2")
    private String spec2;

    @Column(name = "SPEC3")
    private String spec3;

    @Column(name = "SPEC4")
    private String spec4;

    @Column(name = "SPEC5")
    private String spec5;

    @Column(name = "SPEC6")
    private String spec6;

    @Column(name = "SPEC7")
    private String spec7;

    @Column(name = "SPEC8")
    private String spec8;

    @Column(name = "SPEC9")
    private String spec9;

    @Column(name = "SPEC10")
    private String spec10;

    @Column(name = "SPEC11")
    private String spec11;

    @Column(name = "SPEC12")
    private String spec12;

    @Column(name = "SPEC13")
    private String spec13;

    @Column(name = "SPEC14")
    private String spec14;

    @Column(name = "SPEC15")
    private String spec15;

    @Column(name = "SPEC16")
    private String spec16;

    @Column(name = "SPEC17")
    private String spec17;

    @Column(name = "SPEC18")
    private String spec18;

    @Column(name = "SPEC19")
    private String spec19;

    @Column(name = "SPEC20")
    private String spec20;

    @Column(name = "EXTFILEFLAG")
    private String extfileflag;

    @Column(name = "WAVESTRATEGYCODE")
    private String wavestrategycode;

    @Column(name = "WAVESTRATEGYDES")
    private String wavestrategydes;

    @Column(name = "PICKSTGCODE")
    private String pickstgcode;

    @Column(name = "PICKSTGDES")
    private String pickstgdes;

    @Column(name = "AUTOSHPFLAG")
    private String autoshpflag;

    @Column(name = "EDOCUMENTS")
    private String edocuments;

    @Column(name = "GPSX")
    private String gpsx;

    @Column(name = "GPSY")
    private String gpsy;

    @Column(name = "QRANKCODE")
    private String qrankcode;

    @Column(name = "QRANKDES")
    private String qrankdes;

    @Column(name = "DCMONTHS")
    private Long dcmonths;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TAXCODE")
    private String taxcode;

    @Column(name = "TAXDES")
    private String taxdes;

    @Column(name = "VATCOUNTRYNAME")
    private String vatcountryname;

    @Column(name = "CUSTREMARK")
    private String custremark;

    @Column(name = "PIKORDER")
    private Long pikorder;

    @Column(name = "MINEXPDAYS")
    private Long minexpdays;

    @Column(name = "RESERVFLAG")
    private String reservflag;

    @Column(name = "LANG")
    private Long lang;

    @Column(name = "LANGNAME")
    private String langname;

    @Column(name = "EORINUM")
    private String eorinum;

    @Column(name = "RETURNLABELS")
    private String returnlabels;

    @Column(name = "AUJF_SPOUSEDEADATE")
    private Date aujfSpousedeadate;

    @Column(name = "AUJF_TITCODE")
    private String aujfTitcode;

    @Column(name = "AUJF_TITDESC")
    private String aujfTitdesc;

    @Column(name = "AUJF_MARCODE")
    private String aujfMarcode;

    @Column(name = "AUJF_LINKDONOR")
    private String aujfLinkdonor;

    @Column(name = "AUJF_OCCUCODE")
    private String aujfOccucode;

    @Column(name = "AUJF_OCCUDESC")
    private String aujfOccudesc;

    @Column(name = "AUJF_SECCODE")
    private String aujfSeccode;

    @Column(name = "AUJF_SECDESC")
    private String aujfSecdesc;

    @Column(name = "AUJF_OPENDATE")
    private Date aujfOpendate;

    @Column(name = "AUJF_MARRIAGEDATE")
    private Date aujfMarriagedate;

    @Column(name = "AUJF_DEATHDATE")
    private Date aujfDeathdate;

    @Column(name = "AUJF_WORKFAX")
    private String aujfWorkfax;

    @Column(name = "AUJF_CELPHONE")
    private String aujfCelphone;

    @Column(name = "KERN_PARTNERNAME")
    private String kernPartnername;

    @Column(name = "KERN_PARTNERDES")
    private String kernPartnerdes;

    @Column(name = "KERN_FAMILYCODE")
    private String kernFamilycode;

    @Column(name = "AAAA_COLORNAME")
    private String aaaaColorname;

    @Column(name = "AUJF_WORKPHONE")
    private String aujfWorkphone;

    @Column(name = "AUJF_HEADOFFAMILY")
    private String aujfHeadoffamily;

    @Column(name = "AUJF_DONOTCALL")
    private String aujfDonotcall;

    @Column(name = "AUJF_DONTSENTMAIL")
    private String aujfDontsentmail;

    @Column(name = "AUJF_COMMITCODE")
    private String aujfCommitcode;

    @Column(name = "AUJF_COMMITDESC")
    private String aujfCommitdesc;

    @Column(name = "AUJF_PENSIONER")
    private String aujfPensioner;

    @Column(name = "AUJF_DECEASED")
    private String aujfDeceased;

    @Column(name = "AUJF_VOLUNTEER")
    private String aujfVolunteer;

    @Column(name = "KERN_NOTESFLAG")
    private String kernNotesflag;

    @Column(name = "AUJF_APPEAL")
    private String aujfAppeal;

    @Column(name = "KERN_ADDRESSA")
    private String kernAddressa;

    @Column(name = "KERN_STATEA")
    private String kernStatea;

    @Column(name = "KERN_CELLPHONE")
    private String kernCellphone;

    @Column(name = "KERN_LASTORDDATE")
    private Date kernLastorddate;

    @Column(name = "KERN_GENDERCODE")
    private String kernGendercode;

    @Column(name = "KERN_GENDERDES")
    private String kernGenderdes;

    @Column(name = "AUJF_COMPStringNAME")
    private String aujfCompStringname;

    @Column(name = "KERN_PARTNERDIED")
    private String kernPartnerdied;

    @Column(name = "KERN_EMAILNOTSEND")
    private String kernEmailnotsend;

    @Column(name = "KERN_SFID")
    private String kernSfid;

    @Column(name = "KERN_PERMANENTCUST")
    private String kernPermanentcust;

    @Column(name = "KERN_ACSFID")
    private String kernAcsfid;

    @Column(name = "KERN_VIP")
    private String kernVip;

    @Column(name = "KERN_CREATEBYL1")
    private String kernCreatebyl1;

    @Column(name = "KERN_CREATEDATE")
    private String kernCreatedate;

    @Column(name = "KERN_CREATEBYL2")
    private String kernCreatebyl2;

    @Column(name = "KERN_UPDATEDATE")
    private String kernUpdatedate;

    @Column(name = "KERN_CREATEBYSF")
    private Date kernCreatebysf;

    @Column(name = "KERN_CREATEDATESF")
    private Date kernCreatedatesf;

    @Column(name = "CUST")
    private Long cust;

    // --- Navs / Subforms ---
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CustomersA customersA; // CUSTOMERSA_SUBFORM (single)

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CustomersText customersText; // CUSTOMERSTEXT_SUBFORM (single)

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustPlist> custPlists = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustPartDisc> custPartDiscs = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustPartPrice> custPartPrices = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustOrders> custOrders = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustBDomains> custBDomains = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustTreatments> custTreatments = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustNotes> custNotes = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustBalance> custBalances = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustBonuses> custBonuses = new java.util.ArrayList<>();
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustDeals> custDeals = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustFirstQuest> custFirstQuest = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustFobOrders> custFobOrders = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustFobProf> custFobProf = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustPart> custPart = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustCProf> custCProf = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustDiscount> custDiscount = new java.util.ArrayList<>();


    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CustSpec custSpec;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CustWeekday> custWeekday = new java.util.ArrayList<>();
}