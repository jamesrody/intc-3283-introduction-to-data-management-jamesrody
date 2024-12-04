package edu.northwestu.intc3283.datasourcestarter.repository;

import edu.northwestu.intc3283.datasourcestarter.entity.Donor;
import edu.northwestu.intc3283.datasourcestarter.reports.*;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DonorsRepository extends CrudRepository<Donor, Long> {

    List<Donor>  findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    List<Donor> findTop10ByOrderByCreatedAtDesc();

    @Query("""
            
                        SELECT
                              d.first_name AS firstName,
                              d.last_name AS lastName,
                              d.email AS email,
                              YEAR(dn.created_at) AS year,
                              MONTH(dn.created_at) AS month,
                              SUM(dn.amount) AS totalDonationAmount
                          FROM
                              donors d
                          JOIN
                              donations dn ON d.id = dn.donor_id
                          GROUP BY
                              d.id,
                              YEAR(dn.created_at),
                              MONTH(dn.created_At)
                          ORDER BY
                              YEAR(dn.created_at) DESC,
                              MONTH(dn.created_at) DESC,
                              SUM(dn.amount) DESC
                        LIMIT :limit
            """)
    List<TopDonationReportDTO> findTopDonors(@Param("limit")Integer limit);

    @Query("""
    SELECT
        DATE_FORMAT(created_at, '%Y-%u') as donation_week,
        SUM(amount) as                   total_donated
    FROM donations
    WHERE
        created_at BETWEEN :started_at AND :ended_at
    GROUP BY donation_week
    order BY donation_week ASC;
"""
    )

    List<WeeklyDonationRow> weeklyDonationReport(@Param("started_at")LocalDate startedAt,
                                                 @Param("ended_at")LocalDate endedAt);


    @Query("""
    SELECT donor_id,
           d.first_name,
           d.last_name,
           d.address1,
           d.address2,
           d.city,
           d.state,
           d.zip_code,
           COUNT(donor_id) as donation_count,
           SUM(amount) as total_donated
    FROM donations
            INNER JOIN
        nu.donors d on donations.donor_id = d.id
    WHERE donations.created_at >= :started_at and (
        address1 <> '' AND
        address2 <> '' AND
        city <> '' AND
        zip_code <> ''
    
        )
    GROUP BY donor_id
    HAVING donation_count = 1
    
    ORDER BY donation_count DESC;
"""
    )
    List<JaniceQuery> janiceQuery(@Param("started_at")LocalDate startedAt);

    @Query("""
    SELECT donor_id,
           first_name,
           last_name,
           address1,
           address2,
           city,
           state,
           zip_code,
           phone,
           SUM(d.amount)        as total_donated,
           MAX(d.created_at)    as last_donated_on
    FROM nu.donors
            RIGHT JOIN nu.donations d on donors.id = d.donor_id
    WHERE phone <> ''
        AND (
        address1 = ''
            OR address2 = ''
            OR city = ''
            OR state = ''
            OR zip_code = ''
        )
    GROUP BY donor_id;

""")
    List<LarryQuery> larryQuery();

    @Query("""
    SELECT DATE_FORMAT(created_at, '%Y-%U') week_donated,
           SUM(amount) as                   total_donated
    FROM
        donations
    GROUP BY week_donated
    ORDER BY week_donated ASC;
""")
    List<JenniferQueryWeekly> JenniferQueryWeekly();

    @Query("""
    SELECT DATE_FORMAT(created_at, '%Y-%m') month_donated,
           SUM(amount) as                   total_donated
    FROM
        donations
    GROUP BY month_donated
    ORDER BY month_donated ASC;
""")
    List<JenniferQueryMonthly> JenniferQueryMonthly();

    @Query("""
    SELECT donor_id,
           SUM(amount) as total_donated
    FROM
        donations
    WHERE
        created_at BETWEEN '2024-01-01' and '2024-12-31'
    GROUP BY
        donor_id
    ORDER BY
        total_donated DESC
    LIMIT 5;
""")
    List<JenniferQueryTop5> JenniferQueryTop5();

}
